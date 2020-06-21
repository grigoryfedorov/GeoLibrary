package org.grigoryfedorov.geolibrary.distance

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe
import org.grigoryfedorov.geolibrary.RADIUS_POLAR_B_METERS
import org.grigoryfedorov.geolibrary.dto.Point
import kotlin.math.abs

internal class StraightSphericalDistanceCalculatorTest : ShouldSpec({
    should("calculate distance correctly") {
        val radiusCalculator: RadiusCalculator = WGS84RadiusCalculator()
        val distanceCalculator: DistanceCalculator = StraightSphericalDistanceCalculator(radiusCalculator)

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
                    Point(latitude = 90.0, longitude = 45.0, elevation = 0.0),
                    Point(latitude = -90.0, longitude = 45.0, elevation = 0.0), 2 * RADIUS_POLAR_B_METERS
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
            )
        ) { point1, point2, expected ->
            val calculated = distanceCalculator.calculateDistance(point1 = point1, point2 = point2)
            val diff = abs(calculated - expected)
            val diffPercent = if (expected == 0.0) 0.0 else diff / (expected / 100.0)
            println("expected $expected calc $calculated diff $diff perc $diffPercent%")
            diffPercent shouldBe (0.0 plusOrMinus 1.0)
        }
    }
})