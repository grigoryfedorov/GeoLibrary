package org.grigoryfedorov.geolibrary.dto

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import org.grigoryfedorov.geolibrary.LatitudeNormalizer
import org.grigoryfedorov.geolibrary.LongitudeNormalizer
import org.grigoryfedorov.geolibrary.PointTranslator

internal class PointTest : ShouldSpec({

    should("should translate point coordinate using translator") {
        forAll(
                row(Point(latitude = 1.0, longitude = 2.0, elevation = 3.0),
                        Vector(northbound = 4.0, eastbound = 5.0, elevationBound = 6.0),
                        Point(latitude = 5.0, longitude = 7.0, elevation = 9.0))
        ) { point, vector, expected ->
            val pointTranslator = PointTranslator(LatitudeNormalizer(), LongitudeNormalizer())
            val translated = point.translate(vector, pointTranslator)
            translated.shouldBe(expected)
        }
    }

})