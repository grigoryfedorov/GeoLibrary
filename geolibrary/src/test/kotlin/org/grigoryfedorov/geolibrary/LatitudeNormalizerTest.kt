package org.grigoryfedorov.geolibrary

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe

internal class LatitudeNormalizerTest : ShouldSpec({

    should("return same values for correct range [-90;90]") {
        fun checkNormalization(latitude: Double, expected: Double) {
            val latitudeNormalizer = LatitudeNormalizer()
            val normalizedLatitude = latitudeNormalizer.normalizeLatitude(latitude)
            normalizedLatitude shouldBe (expected plusOrMinus 0.000001)
        }

        forAll(
            row(-90.0, -90.0),
            row(-60.0, -60.0),
            row(-30.0, -30.0),
            row(0.0, 0.0),
            row(10.0, 10.0),
            row(30.12456, 30.12456),
            row(60.0, 60.0),
            row(80.0, 80.0),
            row(90.0, 90.0)) { latitude, expected ->
            checkNormalization(latitude, expected)
        }
    }

    // TODO
    should("!return correct values for corner cases") {
        fun checkNormalization(latitude: Double, expected: Double) {
            val latitudeNormalizer = LatitudeNormalizer()
            val normalizedLatitude = latitudeNormalizer.normalizeLatitude(latitude)
            normalizedLatitude shouldBe (expected plusOrMinus 0.000001)
        }

        forAll(
            row(90.0, 90.0),
            row(91.0, 89.0),
            row(120.0, 60.0),
            row(135.0, 45.0),
            row(150.0, 30.0),
            row(179.0, 1.0),
            row(180.0, 0.0),
            row(181.0, -1.0),
            row(200.0, -20.0),
            row(269.0, -89.0),
            row(270.0, -90.0),
            row(271.0, -89.0),
            row(360.0, 0.0),
            row(361.0, 1.0),

            row(-90.0, -90.0),
            row(-91.0, -89.0),
            row(-179.0, -1.0),
            row(-180.0, -0.0),
            row(-181.0, 1.0),
            row(-269.0, 89.0),
            row(-270.0, 90.0),
            row(-271.0, 89.0),
            row(-360.0, 0.0),
            row(-361.0, -1.0)) { latitude, expected ->
            checkNormalization(latitude, expected)
        }
    }
})