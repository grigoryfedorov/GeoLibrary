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

    /**
     * Normalizes longitude to be in correct range and flip in to another hemisphere if needed
     * @see [LONGITUDE_WEST_BOUND] and [LONGITUDE_EAST_BOUND]
     * @param longitude Longitude to normalize in degrees
     * @param needFlip if need to flip to another hemisphere
     * @return normalized and flipped (if needed) longitude in degrees
     */
    fun normalizeLongitude(longitude: Angle, needFlip: Boolean): Angle {
        val flippedLongitude = if (needFlip) {
            if (longitude > 0) {
                longitude - LONGITUDE_FLIP_RANGE
            } else {
                longitude + LONGITUDE_FLIP_RANGE
            }
        } else {
            longitude
        }

        if (flippedLongitude >= LONGITUDE_WEST_BOUND && flippedLongitude < LONGITUDE_EAST_BOUND) {
            return flippedLongitude
        }

        val reminder = flippedLongitude.rem(LONGITUDE_FULL_RANGE)

        return when {
            reminder >= LONGITUDE_EAST_BOUND -> {
                reminder - LONGITUDE_FULL_RANGE
            }
            reminder < LONGITUDE_WEST_BOUND -> {
                reminder + LONGITUDE_FULL_RANGE
            }
            else -> {
                reminder
            }
        }
    }
}
