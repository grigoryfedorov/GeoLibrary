package org.grigoryfedorov.geolibrary

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import org.grigoryfedorov.geolibrary.dto.Point
import org.grigoryfedorov.geolibrary.dto.Rectangle

internal class ContainedInRectangleCalculatorTest : ShouldSpec({

    fun checkContained(rectangle: Rectangle, point: Point, expected: Boolean) {
        val calculator = ContainedInRectangleCalculator()
        val isContained = calculator.isPointContainedInRectangle(rectangle, point)
        isContained shouldBe expected
    }

    should("return correct result for point included in rect") {
        forAll(
            row(
                Rectangle(
                    southWest = Point(latitude = 10.0, longitude = 20.0, elevation = 123.0),
                    northEast = Point(latitude = 20.0, longitude = 30.0, elevation = 345.0)
                ),
                Point(latitude = 15.0, longitude = 25.0, elevation = 0.0),
                true
            ),
            row(
                Rectangle(
                    southWest = Point(latitude = 10.0, longitude = 20.0, elevation = 123.0),
                    northEast = Point(latitude = 20.0, longitude = 30.0, elevation = 345.0)
                ),
                Point(latitude = 10.0, longitude = 25.0, elevation = 0.0),
                true
            ),
            row(
                Rectangle(
                    southWest = Point(latitude = 10.0, longitude = 20.0, elevation = 123.0),
                    northEast = Point(latitude = 20.0, longitude = 30.0, elevation = 345.0)
                ),
                Point(latitude = 15.0, longitude = 20.0, elevation = 0.0),
                true
            ),
            row(
                Rectangle(
                    southWest = Point(latitude = 10.0, longitude = 20.0, elevation = 123.0),
                    northEast = Point(latitude = 20.0, longitude = 30.0, elevation = 345.0)
                ),
                Point(latitude = 15.0, longitude = 19.9999, elevation = 0.0),
                false
            ),
            row(
                Rectangle(
                    southWest = Point(latitude = 10.0, longitude = 20.0, elevation = 123.0),
                    northEast = Point(latitude = 20.0, longitude = 30.0, elevation = 345.0)
                ),
                Point(latitude = 15.0, longitude = 30.0, elevation = 0.0),
                true
            ),
            row(
                Rectangle(
                    southWest = Point(latitude = 10.0, longitude = 20.0, elevation = 123.0),
                    northEast = Point(latitude = 20.0, longitude = 30.0, elevation = 345.0)
                ),
                Point(latitude = 15.0, longitude = 30.0001, elevation = 0.0),
                false
            ),
            row(
                Rectangle(
                    southWest = Point(latitude = 10.0, longitude = 20.0, elevation = 123.0),
                    northEast = Point(latitude = 20.0, longitude = 30.0, elevation = 345.0)
                ),
                Point(latitude = 9.9999, longitude = 25.0, elevation = 0.0),
                false
            ),
            row(
                Rectangle(
                    southWest = Point(latitude = 10.0, longitude = 20.0, elevation = 123.0),
                    northEast = Point(latitude = 20.0, longitude = 30.0, elevation = 345.0)
                ),
                Point(latitude = 20.00001, longitude = 25.0, elevation = 0.0),
                false
            ),
            row(
                Rectangle(
                    southWest = Point(latitude = 10.0, longitude = 170.0, elevation = 123.0),
                    northEast = Point(latitude = 20.0, longitude = -170.0, elevation = 345.0)
                ),
                Point(latitude = 15.0, longitude = 180.0, elevation = 0.0),
                true
            ),
            row(
                Rectangle(
                    southWest = Point(latitude = 10.0, longitude = 170.0, elevation = 123.0),
                    northEast = Point(latitude = 20.0, longitude = -170.0, elevation = 345.0)
                ),
                Point(latitude = 15.0, longitude = -175.0, elevation = 0.0),
                true
            ),
            row(
                Rectangle(
                    southWest = Point(latitude = 10.0, longitude = 170.0, elevation = 123.0),
                    northEast = Point(latitude = 20.0, longitude = -170.0, elevation = 345.0)
                ),
                Point(latitude = 15.0, longitude = -170.0, elevation = 0.0),
                true
            ),
            row(
                Rectangle(
                    southWest = Point(latitude = 10.0, longitude = 170.0, elevation = 123.0),
                    northEast = Point(latitude = 20.0, longitude = -170.0, elevation = 345.0)
                ),
                Point(latitude = 15.0, longitude = 170.0, elevation = 0.0),
                true
            ),
            row(
                Rectangle(
                    southWest = Point(latitude = 10.0, longitude = 170.0, elevation = 123.0),
                    northEast = Point(latitude = 20.0, longitude = -170.0, elevation = 345.0)
                ),
                Point(latitude = 15.0, longitude = -180.0, elevation = 0.0),
                true
            ),
            row(
                Rectangle(
                    southWest = Point(latitude = 10.0, longitude = 170.0, elevation = 123.0),
                    northEast = Point(latitude = 20.0, longitude = -170.0, elevation = 345.0)
                ),
                Point(latitude = 15.0, longitude = -180.0001, elevation = 0.0),
                false
            ),
            row(
                Rectangle(
                    southWest = Point(latitude = 10.0, longitude = 170.0, elevation = 123.0),
                    northEast = Point(latitude = 20.0, longitude = -170.0, elevation = 345.0)
                ),
                Point(latitude = 15.0, longitude = 169.0, elevation = 0.0),
                false
            ),
            row(
                Rectangle(
                    southWest = Point(latitude = 10.0, longitude = 170.0, elevation = 123.0),
                    northEast = Point(latitude = 20.0, longitude = -170.0, elevation = 345.0)
                ),
                Point(latitude = 15.0, longitude = -169.0, elevation = 0.0),
                false
            ),
            row(
                Rectangle(
                    southWest = Point(latitude = 10.0, longitude = 170.0, elevation = 123.0),
                    northEast = Point(latitude = 20.0, longitude = -170.0, elevation = 345.0)
                ),
                Point(latitude = 9.9999, longitude = 180.0, elevation = 0.0),
                false
            ),
            row(
                Rectangle(
                    southWest = Point(latitude = 10.0, longitude = 170.0, elevation = 123.0),
                    northEast = Point(latitude = 20.0, longitude = -170.0, elevation = 345.0)
                ),
                Point(latitude = 10.0, longitude = 180.0, elevation = 0.0),
                true
            ),
            row(
                Rectangle(
                    southWest = Point(latitude = 10.0, longitude = 170.0, elevation = 123.0),
                    northEast = Point(latitude = 20.0, longitude = -170.0, elevation = 345.0)
                ),
                Point(latitude = 10.0, longitude = 180.0001, elevation = 0.0),
                false
            ),
            row(
                Rectangle(
                    southWest = Point(latitude = 10.0, longitude = 170.0, elevation = 123.0),
                    northEast = Point(latitude = 20.0, longitude = -170.0, elevation = 345.0)
                ),
                Point(latitude = 20.0001, longitude = 180.0, elevation = 0.0),
                false
            ),
            row(
                Rectangle(
                    southWest = Point(latitude = 10.0, longitude = 170.0, elevation = 123.0),
                    northEast = Point(latitude = 20.0, longitude = -170.0, elevation = 345.0)
                ),
                Point(latitude = 20.0, longitude = 180.0, elevation = 0.0),
                true
            )


        ) { rectangle, point, expected ->
            checkContained(rectangle, point, expected)
        }
    }


})