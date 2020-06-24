package org.grigoryfedorov.geolibrary

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe

internal class LongitudeSpanCalculatorTest: ShouldSpec({

    fun checkMinSpan(longitude1: Angle, longitude2: Angle, expectedSpan: Angle) {
        val longitudeSpanCalculator = LongitudeSpanCalculator()
        val span = longitudeSpanCalculator.calculateMinLongitudeSpan(
            longitude1 = longitude1,
            longitude2 = longitude2
        )
        span shouldBe (expectedSpan plusOrMinus 0.000001)
    }

    fun checkOrientedSpan(west: Angle, east: Angle, expectedSpan: Angle) {
        val longitudeSpanCalculator = LongitudeSpanCalculator()
        val span = longitudeSpanCalculator.calculateOrientedLongitudeSpan(
            west = west,
            east = east
        )
        span shouldBe (expectedSpan plusOrMinus 0.000001)
    }


    should("calculate min span correctly for same and different hemispheres") {
        forAll(
            row(0.0, 0.0, 0.0),
            row(90.0, 90.0, 0.0),
            row(123.456, 123.456, 0.0),
            row(180.0, 180.0, 0.0),
            row(0.0, 10.0, 10.0),
            row(0.0, -10.0, 10.0),
            row(10.0, 0.0, 10.0),
            row(-10.0, 0.0, 10.0),
            row(-10.0, 10.0, 20.0),
            row(10.0, -10.0, 20.0),
            row(0.0, 180.0, 180.0),
            row(180.0, 0.0, 180.0),
            row(0.0, -180.0, 180.0),
            row(-180.0, 0.0, 180.0),
            row(160.0, -150.0, 50.0),
            row(-179.0, 179.0, 2.0),
            row(179.0, -179.0, 2.0)
        ) { longitude1, longitude2, expectedSpan ->
            checkMinSpan(
                longitude1 = longitude1,
                longitude2 = longitude2,
                expectedSpan = expectedSpan
            )
        }
    }

    should("calculate oriented span correctly for same and different hemispheres") {
        forAll(
            row(0.0, 0.0, 0.0),
            row(90.0, 90.0, 0.0),
            row(123.456, 123.456, 0.0),
            row(180.0, 180.0, 0.0),
            row(0.0, 10.0, 10.0),
            row(0.0, -10.0, 350.0),
            row(10.0, 0.0, 350.0),
            row(-10.0, 0.0, 10.0),
            row(-10.0, 10.0, 20.0),
            row(10.0, -10.0, 340.0),
            row(0.0, 180.0, 180.0),
            row(180.0, 0.0, 180.0),
            row(0.0, -180.0, 180.0),
            row(-180.0, 0.0, 180.0),
            row(160.0, -150.0, 50.0),
            row(-150.0, 160.0, 310.0),
            row(-179.0, 179.0, 358.0),
            row(179.0, -179.0, 2.0)
        ) { west, east, expectedSpan ->
            checkOrientedSpan(
                west = west,
                east = east,
                expectedSpan = expectedSpan
            )
        }
    }
})