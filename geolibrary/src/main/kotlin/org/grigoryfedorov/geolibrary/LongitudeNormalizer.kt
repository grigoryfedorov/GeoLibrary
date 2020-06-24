package org.grigoryfedorov.geolibrary


/**
 * Longitudes returned by this library (output) are always in the range [-180, 180> (left
 * inclusive, right exclusive).
 * Longitudes passed to this library (input) may be outside this range and are automatically
 * wrapped to the range by the library.
 *
 * https://stackoverflow.com/a/31125984
 */
class LongitudeNormalizer {
    fun normalizeLongitude(longitude: Angle, needFlip: Boolean): Angle {
        val flippedLongitude = if (needFlip) {
            if (longitude > 0) {
                longitude - ANGLE_180_DEGREES
            } else {
                longitude + ANGLE_180_DEGREES
            }
        } else {
            longitude
        }

        if (flippedLongitude >= LONGITUDE_WEST_BOUND && flippedLongitude < LONGITUDE_EAST_BOUND) {
            return flippedLongitude
        }

        val reminder = flippedLongitude.rem(LONGITUDE_FULL_CIRCLE)

        return when {
            reminder >= LONGITUDE_EAST_BOUND -> {
                reminder - LONGITUDE_FULL_CIRCLE
            }
            reminder < LONGITUDE_WEST_BOUND -> {
                reminder + LONGITUDE_FULL_CIRCLE
            }
            else -> {
                reminder
            }
        }
    }
}
