package org.grigoryfedorov.geolibrary

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe

internal class LatitudeNormalizerTest : ShouldSpec({

    fun checkNormalization(latitude: Angle, expected: Angle, flipLongitude: Boolean) {
        val latitudeNormalizer = LatitudeNormalizer()
        val normalizedLatitude = latitudeNormalizer.normalizeLatitude(latitude)
        normalizedLatitude.latitude shouldBe (expected plusOrMinus 0.000001)
        normalizedLatitude.flipLongitude shouldBe flipLongitude
    }

    should("return same values for correct range [-90;90]") {
        forAll(
            row(-90.0, -90.0, false),
            row(-60.0, -60.0, false),
            row(-30.0, -30.0, false),
            row(0.0, 0.0, false),
            row(10.0, 10.0, false),
            row(30.12456, 30.12456, false),
            row(60.0, 60.0, false),
            row(80.0, 80.0, false),
            row(90.0, 90.0, false)) { latitude, expected, flip ->
            checkNormalization(latitude, expected, flip)
        }
    }

    // TODO
    should("return correct values for corner cases") {
        forAll(
            row(90.0, 90.0, false),
            row(91.0, 89.0, true),
            row(120.0, 60.0, true),
            row(135.0, 45.0, true),
            row(150.0, 30.0, true),
            row(179.0, 1.0, true),
            row(180.0, 0.0, true),
            row(181.0, -1.0, true),
            row(200.0, -20.0, true),
            row(269.0, -89.0, true),
            row(270.0, -90.0, false),
            row(271.0, -89.0, false),
            row(360.0, 0.0, false),
            row(361.0, 1.0, false),

            row(-90.0, -90.0, false),
            row(-91.0, -89.0, true),
            row(-179.0, -1.0, true),
            row(-180.0, -0.0, true),
            row(-181.0, 1.0, true),
            row(-269.0, 89.0, true),
            row(-270.0, 90.0, false),
            row(-271.0, 89.0, false),
            row(-360.0, 0.0, false),
            row(-361.0, -1.0, false)) { latitude, expected, flipLongitude ->
            checkNormalization(latitude, expected, flipLongitude)
        }
    }
})