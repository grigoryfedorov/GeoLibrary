package org.grigoryfedorov.geolibrary.dto

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import org.grigoryfedorov.geolibrary.PointTranslator

internal class PolyLineTest : ShouldSpec() {

    lateinit var pointTranslator: PointTranslator

    lateinit var point1: Point
    lateinit var point2: Point
    lateinit var point3: Point
    lateinit var point4: Point
    lateinit var point5: Point
    lateinit var point6: Point

    init {
        beforeTest {
            pointTranslator = mockk()

            point1 = Point(1.2323, 4.32523523, 523523.5235)
            point2 = Point(2.5235, 6.32523523, 4684.5235)
            point3 = Point(3.47547, 7.32523523, 879.5235)
            point4 = Point(4.976976, 8.32523523, 1321.5235)
            point5 = Point(5.23523, 9.32523523, 989.5235)
            point6 = Point(6.3466, 10.32523523, 164.5235)
        }

        should("return list correct list of lines") {
            forAll(
                row(
                    // PolyLine factory checks min input points count
                    // And constructor is internal, so empty input list is not gonna happen
                    PolyLine(listOf()),
                    listOf()
                ),
                row(
                    PolyLine(listOf(point1, point2)),
                    listOf(Line(point1, point2))
                ),
                row(
                    PolyLine(listOf(point1, point2, point3, point4, point5, point6)),
                    listOf(
                        Line(point1, point2),
                        Line(point2, point3),
                        Line(point3, point4),
                        Line(point4, point5),
                        Line(point5, point6)
                    )
                ),
                row(
                    PolyLine(listOf(point6, point4, point2, point1)),
                    listOf(
                        Line(point6, point4),
                        Line(point4, point2),
                        Line(point2, point1)
                    )
                )
            ) { polyLine, expected ->

                val lines = polyLine.toLines()

                lines shouldBe expected

            }
        }

        should("translate by points using mocked point translator") {
            forAll(
                row(
                    PolyLine(listOf(point1, point2, point3, point4, point5, point6)),
                    Vector(5235.43, 96.244, 200.0),
                    PolyLine(listOf(point6, point5, point4, point3, point2, point1))
                ),
                row(
                    PolyLine(listOf(point1, point2, point3)),
                    Vector(5235.43, 96.244, 200.0),
                    PolyLine(listOf(point4, point5, point6))
                )
            ) { polyline, vector, expected ->
                run {
                    val polyLineIter = polyline.points.iterator()
                    val expectedIter = expected.points.iterator()

                    while(polyLineIter.hasNext()) {
                        every {
                            pointTranslator.translate(polyLineIter.next(), vector)
                        } returns expectedIter.next()
                    }
                }

               val translated = polyline.translate(vector, pointTranslator)

                val translatedIter = translated.points.iterator()
                val expectedIter = expected.points.iterator()
                while(translatedIter.hasNext()) {
                    translatedIter.next().shouldBe(expectedIter.next())
                }

                clearMocks(pointTranslator)
            }
        }

    }

}