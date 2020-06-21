package org.grigoryfedorov.geolibrary.distance

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.data.row
import io.kotest.data.forAll
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe
import org.grigoryfedorov.geolibrary.RADIUS_EQUATORIAL_A_METERS
import org.grigoryfedorov.geolibrary.RADIUS_POLAR_B_METERS

internal class WGS84RadiusCalculatorTest : ShouldSpec({
    should("calculate radius correctly") {
        val radiusCalculator: RadiusCalculator = WGS84RadiusCalculator()

        val r45 = 6367489.5438

        forAll(
            row(0.0, RADIUS_EQUATORIAL_A_METERS),
            row(90.0, RADIUS_POLAR_B_METERS),
            row(-90.0, RADIUS_POLAR_B_METERS),
            row(45.0,  r45),
            row(-45.0, r45)
        ) { latitude, radius ->
            radiusCalculator.getRadius(latitude) shouldBe(radius plusOrMinus(0.5))
        }
    }
}



)