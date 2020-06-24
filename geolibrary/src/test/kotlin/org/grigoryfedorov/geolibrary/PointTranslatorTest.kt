package org.grigoryfedorov.geolibrary

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import org.grigoryfedorov.geolibrary.dto.Point
import org.grigoryfedorov.geolibrary.dto.Vector

internal class PointTranslatorTest : ShouldSpec({

    fun checkTranslationCalculation(point: Point, vector: Vector, expected: Point) {
        val pointTranslator = PointTranslator(LatitudeNormalizer(), LongitudeNormalizer())
        val translated = pointTranslator.translate(point, vector)
        translated.shouldBe(expected)
    }

    should("calculate translation correctly") {
        forAll(
            row(
                Point(latitude = 1.0, longitude = 2.0, elevation = 3.0),
                Vector(northbound = 0.0, eastbound = 0.0, elevationBound = 0.0),
                Point(latitude = 1.0, longitude = 2.0, elevation = 3.0)
            )

        ) { point, vector, expected ->
            checkTranslationCalculation(point, vector, expected)
        }
    }
})