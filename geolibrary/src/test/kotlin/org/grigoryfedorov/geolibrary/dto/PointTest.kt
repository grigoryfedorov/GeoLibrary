package org.grigoryfedorov.geolibrary.dto

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.data.blocking.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import org.grigoryfedorov.geolibrary.PointTranslator

internal class PointTest : ShouldSpec() {

    lateinit var pointTranslator: PointTranslator

    init {

        beforeTest {
            pointTranslator = mockk()
        }

        should("should translate point coordinate using mocked translator") {
            forAll(
                row(
                    Point(latitude = 1.0, longitude = 2.0, elevation = 3.0),
                    Vector(northbound = 4.0, eastbound = 5.0, elevationBound = 6.0),
                    Point(latitude = 5.0, longitude = 7.0, elevation = 9.0)
                ),
                row(
                    Point(latitude = 523.0, longitude = 5235.0, elevation = 646.0),
                    Vector(northbound = 662.0, eastbound = 252.0, elevationBound = 346.0),
                    Point(latitude = 34.0, longitude = 46.0, elevation = 6346.0)
                )
            ) { point, vector, expected ->

                every {
                    pointTranslator.translate(point, vector)
                } returns expected

                val translated = point.translate(vector, pointTranslator)
                translated.shouldBe(expected)

                clearMocks(pointTranslator)
            }
        }
    }

}