package org.grigoryfedorov.geolibrary

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe

internal class LatitudeSpanCalculatorTest : ShouldSpec({

    fun checkSpanCalculation(latitude1: Angle, latitude2: Angle, expected: Angle) {
        val latitudeSpanCalculator = LatitudeSpanCalculator()
        val span = latitudeSpanCalculator.calculateLatitudeSpan(latitude1, latitude2)
        span shouldBe(expected plusOrMinus ANGLE_EQUAL_ACCURACY)
    }

    should("") {
        forAll(
            row(-90.0, 90.0, 180.0),
            row(90.0, -90.0, 180.0),
            row(90.0, 90.0, 0.0),
            row(0.0, 0.0, 0.0),
            row(30.0, 50.0, 20.0),
            row(50.0, 30.0, 20.0),
            row(-20.0, -40.0, 20.0),
            row(-40.0, -20.0, 20.0),
            row(-10.0, 15.0, 25.0),
            row(-15.0, 10.0, 25.0)

        ) { latitude, expected, flip ->
            checkSpanCalculation(latitude, expected, flip)
        }
    }
})