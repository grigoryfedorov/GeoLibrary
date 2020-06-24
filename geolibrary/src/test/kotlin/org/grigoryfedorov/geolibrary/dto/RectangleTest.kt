package org.grigoryfedorov.geolibrary.dto

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import org.grigoryfedorov.geolibrary.ContainedInRectangleCalculator
import org.grigoryfedorov.geolibrary.PointTranslator
import org.grigoryfedorov.geolibrary.shouldBe

internal class RectangleTest : ShouldSpec() {

    lateinit var pointTranslator: PointTranslator
    lateinit var containedInRectangleCalculator: ContainedInRectangleCalculator

    init {
        beforeTest {
            pointTranslator = mockk()
            containedInRectangleCalculator = mockk()
        }

        should("translate rectangle by point using mocked PointTranslator") {
            forAll(
                row(
                    Rectangle(
                        Point(-15.5, -13.3, -47.7),
                        Point(67.4, 153.23, 5753.52)
                    ),
                    Vector(55.5, 66.6, 77.7),
                    Rectangle(
                        Point(-12.2, -18.8, -45.5),
                        Point(43.5, 123.53, 523.52)
                    )
                )
            ) { rectangle, vector, expected ->
                every {
                    pointTranslator.translate(rectangle.northEast, vector)
                } returns expected.northEast

                every {
                    pointTranslator.translate(rectangle.southWest, vector)
                } returns expected.southWest

                val translated = rectangle.translate(vector, pointTranslator)

                translated.southWest.shouldBe(expected.southWest)
                translated.northEast.shouldBe(expected.northEast)

                clearMocks(pointTranslator)
            }
        }

        should("detect point inside rectangle using mocked ContainedInRectangleCalculator") {
            forAll(
                row(
                    Rectangle(
                        Point(-15.5, -13.3, -47.7),
                        Point(67.4, 153.23, 5753.52)
                    ),
                    Point(123.456, 098.765, 5235.525),
                    true
                ),
                row(
                    Rectangle(
                        Point(-12.2, -18.8, -45.5),
                        Point(43.5, 123.53, 523.52)
                    ),
                    Point(0.0, 2.2, 3.3),
                    true
                )
            ) { rectangle, point, expected ->

                every {
                    containedInRectangleCalculator.isPointContainedInRectangle(rectangle, point)
                } returns expected

                val contains = rectangle.contains(point, containedInRectangleCalculator)

                contains shouldBe expected

                clearMocks(containedInRectangleCalculator)
            }
        }
    }

}