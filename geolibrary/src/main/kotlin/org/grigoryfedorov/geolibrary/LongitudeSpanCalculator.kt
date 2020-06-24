package org.grigoryfedorov.geolibrary

import kotlin.math.abs

/**
 * Calculate span (delta) in degrees for two longitudes.
 * Assume that both longitudes are already normalized in range
 * Span is always positive
 * The tricky case is when longitudes are in different hemispheres
 */
class LongitudeSpanCalculator {
    fun calculateMinLongitudeSpan(longitude1: Angle, longitude2: Angle): Angle {
        return if (isLongitudesInDifferentHemispheres(longitude1, longitude2)) {
            minOf(
                LONGITUDE_FULL_RANGE - abs(longitude1) - abs(longitude2),
                abs(longitude1 - longitude2)
            )
        } else {
            abs(longitude2 - longitude1)
        }
    }

    private fun isLongitudesInDifferentHemispheres(
        longitude1: Angle,
        longitude2: Angle
    ) = (longitude1 < 0 && longitude2 > 0
                || longitude1 > 0 && longitude2 < 0)

    fun calculateOrientedLongitudeSpan(west: Angle, east: Angle): Angle {
        return if (east < west) {
            LONGITUDE_FULL_RANGE - abs(west) - abs(east)
        } else {
            east - west
        }
    }
}
