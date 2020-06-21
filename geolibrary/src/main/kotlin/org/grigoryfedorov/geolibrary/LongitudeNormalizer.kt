package org.grigoryfedorov.geolibrary

/**
 * Longitudes returned by this library (output) are always in the range [-180, 180> (left
 * inclusive, right exclusive).
 * Longitudes passed to this library (input) may be outside this range and are automatically
 * wrapped to the range by the library.
 */
class LongitudeNormalizer {
    fun normalizeLongitude(longitude: Angle): Angle {
        return if (longitude < LONGITUDE_WEST_BOUND) {
            val reminder = longitude.rem(LONGITUDE_FULL_CIRCLE)
            if (reminder >= LONGITUDE_WEST_BOUND) {
                reminder
            } else {
                reminder + LONGITUDE_FULL_CIRCLE
            }

        } else if (longitude >= LONGITUDE_EAST_BOUND) {
            val reminder = longitude.rem(LONGITUDE_FULL_CIRCLE)
            if (reminder < LONGITUDE_EAST_BOUND) {
                reminder
            } else {
                reminder - LONGITUDE_FULL_CIRCLE
            }
        } else {
            longitude
        }
    }
}
