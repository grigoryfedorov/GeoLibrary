package org.grigoryfedorov.geolibrary

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe

internal class LongitudeNormalizerTestShouldSpec : ShouldSpec({
    fun checkNormalization(longitude: Double, expected: Double) {
        val longitudeNormalizer = LongitudeNormalizer()
        val normalizedLongitude = longitudeNormalizer.normalizeLongitude(longitude, needFlip = false)
        normalizedLongitude shouldBe (expected plusOrMinus 0.000001)
    }

    should("return same values for correct range [-180;180>") {
        forAll(
            row(
                -180.0, -180.0
            ),
            row(
                -120.0, -120.0
            ),
            row(
                -90.0, -90.0
            ),
            row(
                -45.0, -45.0
            ),
            row(
                0.0, -0.0
            ),
            row(
                30.12456, 30.12456
            ),
            row(
                100.0, 100.0
            ),
            row(
               179.0, 179.0
            )
        ) { longitude, expected ->
            checkNormalization(longitude, expected)
        }
    }

    should("return correct values for edge cases") {
        forAll(
            row(
                0.0, 0.0
            ),
            row(
                0.00001, 0.00001
            ),
            row(
                -0.00001, -0.00001
            ),
            row(
                1.23456, 1.23456
            ),
            row(
                -1.23456, -1.23456
            ),
            row(
                179.0, 179.0
            ),
            row(
                179.9999, 179.9999
            ),
            row(
                180.0, -180.0
            ),
            row(
                180.0001, -179.9999
            ),
            row(
                181.0, -179.0
            ),
            row(
                -179.0, -179.0
            ),
            row(
                -179.9999, -179.9999
            ),
            row(
                -180.0, -180.0
            ),
            row(
                -180.0001, 179.9999
            ),
            row(
                -181.0, 179.0
            ),
            row(
                359.0, -1.0
            ),
            row(
                359.9999, -0.0001
            ),
            row(
                360.0, 0.0
            ),
            row(
                360.0001, 0.0001
            ),
            row(
                361.0, 1.0
            ),
            row(
                -359.0, 1.0
            ),
            row(
                -359.9999, 0.0001
            ),
            row(
                -360.0, 0.0
            ),
            row(
                -360.0001, -0.0001
            ),
            row(
                -361.0, -1.0
            )
        ) { longitude, expected ->
            checkNormalization(longitude, expected)
        }
    }

    should("return same as input after n * 360 subtract for positive [0; 180>") {
        forAll(
            row(
                360.0, 0.0
            ),
            row(
                2 * 360.0 + 0.00001, 0.00001
            ),
            row(
                3 * 360.0 + 30.12345, 30.12345
            ),
            row(
                4 * 360.0 + 90.0, 90.0
            ),
            row(
                100 * 360.0 + 74.23523, 74.23523
            ),
            row(
                10000 * 360.0 + 179.9999, 179.9999
            )

        ) { longitude, expected ->
            checkNormalization(longitude, expected)
        }
    }

    should("return same as input after n * 360 subtract for negative cases [-180; 0>") {
        forAll(
            row(
                -360.00001, -0.00001
            ),
            row(
                -360 -30.12345, -30.12345
            ),
            row(
                -360 * 2 -90.0, -90.0
            ),
            row(
                -360 * 3 -74.23523, -74.23523
            ),
            row(
                -360 * 5 -179.9999, -179.9999
            ),
            row(
                -360 * 10000 -180.0, -180.0
            )
        ) { longitude, expected ->
            checkNormalization(longitude, expected)
        }
    }

    should("return normalized longitude for different hemisphere  180; 360") {
        forAll(
            row(
                200.0, -160.0
            ),
            row(
                250.1245, -109.8755
            ),
            row(
                300.0, -60.0
            ),
            row(
                350.0, -10.0
            )
        ) { longitude, expected ->
            checkNormalization(longitude, expected)
        }
    }

    should("return normalized longitude for different hemisphere  -180; -360") {
        forAll(
            row(
                -200.0, 160.0
            ),
            row(
                -250.1245, 109.8755
            ),
            row(
                -300.0, 60.0
            ),
            row(
                -350.0, 10.0
            )
        ) { longitude, expected ->
            checkNormalization(longitude, expected)
        }
    }

})
