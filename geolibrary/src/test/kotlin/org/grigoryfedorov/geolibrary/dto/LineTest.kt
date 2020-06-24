package org.grigoryfedorov.geolibrary.dto

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import org.grigoryfedorov.geolibrary.DISTANCE_EQUAL_ACCURACY
import org.grigoryfedorov.geolibrary.PointTranslator
import org.grigoryfedorov.geolibrary.distance.DistanceCalculator

internal class LineTest : ShouldSpec() {

    lateinit var distanceCalculator: DistanceCalculator
    lateinit var pointTranslator: PointTranslator

    init {

        beforeTest {
            distanceCalculator = mockk()
            pointTranslator = mockk()
        }

        should("calculate length using mocked DistanceCalculator") {
            forAll(
                    row(Line(Point(10.0, 20.0, 30.0),
                            Point(20.0, 30.0, 40.0)), 100.0),
                    row(Line(Point(100.52350, -54.52350, 0.0),
                            Point(-150.0, 0.0, 1000.0)), 999.4545)
            ) { line, expected ->
                every {
                    distanceCalculator.calculateDistance(line.point1, line.point2)
                } returns expected

                val length = line.length(distanceCalculator)
                length shouldBe (expected plusOrMinus DISTANCE_EQUAL_ACCURACY)

                clearMocks(distanceCalculator)
            }
        }

        should("translate line using mocked PointTranslator") {

            forAll(
                    row(Line(Point(10.0, 20.0, 30.0),
                            Point(20.0, 30.0, 40.0)),
                            Vector(30.0, 40.0, 50.0),
                            Line(Point(999.0, 888.0, 777.0),
                                    Point(111.0, 222.0, 333.0))
                    ),
                    row(Line(Point(100.52350, -54.52350, 0.0),
                            Point(-150.0, 0.0, 1000.0)),
                            Vector(30.0, 40.0, 50.0),
                            Line(Point(10.0, 20.0, 30.0),
                                    Point(20.0, 30.0, 40.0))
                    )
            ) { line, vector, expected ->

                every {
                    pointTranslator.translate(line.point1, vector)
                } returns expected.point1
                every {
                    pointTranslator.translate(line.point2, vector)
                } returns expected.point2

                val translated = line.translate(vector, pointTranslator)
                translated.point1.shouldBe(expected.point1)
                translated.point2.shouldBe(expected.point2)

                clearMocks(pointTranslator)
            }
        }
    }
}