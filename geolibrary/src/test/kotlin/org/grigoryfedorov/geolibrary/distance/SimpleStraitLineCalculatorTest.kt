package org.grigoryfedorov.geolibrary.distance

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe
import org.grigoryfedorov.geolibrary.dto.Line
import org.grigoryfedorov.geolibrary.dto.Point
import org.grigoryfedorov.geolibrary.extentions.format
import kotlin.math.abs
import kotlin.math.sqrt

internal class SimpleStraitLineCalculatorTest: ShouldSpec({
    should("calculate distance correctly") {
        val distanceCalculator: DistanceCalculator = SimpleStraitLineCalculator()

        forAll(
                row(
                        Point(latitude = 45.0, longitude = 45.0, elevation = 0.0),
                        Point(latitude = 45.0, longitude = 45.0, elevation = 0.0), 0.0
                ),
                row(
                        Point(latitude = 45.0, longitude = 45.0, elevation = 0.0),
                        Point(latitude = 45.0, longitude = 45.0, elevation = 100.0), 100.0
                ),
                row(
                        Point(latitude = 45.0, longitude = 45.0, elevation = 100.0),
                        Point(latitude = 45.0, longitude = 45.0, elevation = -100.0), 200.0
                ),
                row(
                        Point(latitude = 45.0, longitude = 45.0, elevation = 0.0),
                        Point(latitude = 45.0, longitude = 46.0, elevation = 0.0), 78630.0
                ),
                row(
                        Point(latitude = 89.0, longitude = 45.0, elevation = 0.0),
                        Point(latitude = 89.0, longitude = 46.0, elevation = 0.0), 1941.0
                ),
                row(
                        Point(latitude = 85.0, longitude = 45.0, elevation = 0.0),
                        Point(latitude = 85.0, longitude = 46.0, elevation = 0.0), 9691.0
                ),
                row(
                        Point(latitude = 0.0, longitude = 45.0, elevation = 0.0),
                        Point(latitude = 0.0, longitude = 46.0, elevation = 0.0), 111200.0
                ),
                row(
                        Point(latitude = 49.9917, longitude = 8.41321, elevation = 0.0),
                        Point(latitude = 50.0049, longitude = 8.42182, elevation = 0.0), 1593.0
                ),
                row(
                        Point(latitude = 52.5164, longitude = 13.3777, elevation = 0.0),
                        Point(latitude = 38.692668, longitude = -9.177944, elevation = 0.0), 2317722.0
                ),
                row(
                        Point(latitude = 52.3695334, longitude = 4.8949987, elevation = 0.0),
                        Point(latitude = 52.3602075, longitude = 4.8869903, elevation = 0.0), 1172.382
                ),


                // https://www.cqsrg.org/tools/GCDistance/
                row(
                        Point(latitude = 85.0, longitude = 60.0, elevation = 0.0),
                        Point(latitude = 85.0, longitude = 61.0, elevation = 0.0), 9734.400
                ),

                row(
                        Point(latitude = 85.0, longitude = 60.0, elevation = 0.0),
                        Point(latitude = 84.5, longitude = 61.345, elevation = 0.0), 57505.372
                )

        ) { point1, point2, expected ->
            val calculated = distanceCalculator.calculateDistance(point1 = point1, point2 = point2)
            val diff = abs(calculated - expected)
            val diffPercent = if (expected == 0.0) {
                diff
            } else {
                diff / expected * 100.0
            }
            println("${point1.latitude}:${point1.longitude} - ${point2.latitude}:${point2.longitude} exp ${expected.format(3)} calc ${calculated.format(3)} diff ${diff.format(3)} perc ${diffPercent.format(2)}%")
            diffPercent shouldBe (0.0 plusOrMinus 1.0)
        }
    }

    should("find point on line") {
        val pointOnLineFinder: PointOnLineFinder = SimpleStraitLineCalculator()

        forAll(
                row(
                        Line(
                                point1 = Point(latitude = 0.0, longitude = 60.0, elevation = 0.0),
                                point2 = Point(latitude = 0.0, longitude = 61.0, elevation = 0.0)
                        ), 111300.0 / 2.0
                ),
                row(
                        Line(
                                point1 = Point(latitude = 85.0, longitude = 60.0, elevation = 0.0),
                                point2 = Point(latitude = 84.0, longitude = 60.0, elevation = 0.0)
                        ), 111300.0 / 2.0
                ),
                row(
                        Line(
                                point1 = Point(latitude = 0.0, longitude = 60.0, elevation = 0.0),
                                point2 = Point(latitude = 1.0, longitude = 61.0, elevation = 0.0)
                        ), 111300.0 * sqrt(2.0)
                ),
                row(
                        Line(
                                point1 = Point(latitude = 0.0, longitude = 60.0, elevation = 0.0),
                                point2 = Point(latitude = 0.0, longitude = 60.0, elevation = 300.0)
                        ), 200.0

                )
        ) { line, distance ->
            val point = pointOnLineFinder.findPointOnLineByDistance(line, distance)
            println("Found $point")
//            radiusCalculator.getRadius(latitude) shouldBe (radius plusOrMinus (0.5))
        }
    }
})