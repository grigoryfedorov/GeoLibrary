package org.grigoryfedorov.geolibrary

import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe
import org.grigoryfedorov.geolibrary.dto.Point

internal fun Point.shouldBe(expected: Point) {
    latitude shouldBe (expected.latitude plusOrMinus ANGLE_EQUAL_ACCURACY)
    longitude shouldBe (expected.longitude plusOrMinus ANGLE_EQUAL_ACCURACY)
    elevation shouldBe (expected.elevation plusOrMinus ELEVATION_EQUAL_ACCURACY)
}

internal fun Double.format(digits: Int) = "%.${digits}f".format(this)