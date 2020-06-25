package org.grigoryfedorov.geolibrary

/**
 * Normalized latitude to be in correct range
 *
 * see https://stackoverflow.com/a/31125984
 */
class LatitudeNormalizer {

    /**
     * Normalize latitude to correct range,
     * @see [LATITUDE_SOUTH_BOUND] and [LATITUDE_NORTH_BOUND]
     * @param latitude Latitude in degrees to be normalized
     * @return normalized latitude (if needed)
     * and flip flag if there is a need to flip longitude to another hemisphere
     */
    fun normalizeLatitude(latitude: Angle): NormalizedLatitude {
        if (latitude in LATITUDE_NORTH_BOUND..LATITUDE_NORTH_BOUND) {
            return NormalizedLatitude(
                latitude = latitude,
                flipLongitude = false
            )
        }

        val reminder = latitude.rem(LATITUDE_ROUND_CIRCLE)

        val circleNormalized = when {
            reminder > LATITUDE_FULL_RANGE -> {
                reminder - LATITUDE_ROUND_CIRCLE
            }
            reminder < -LATITUDE_FULL_RANGE -> {
                reminder + LATITUDE_ROUND_CIRCLE
            }
            else -> {
                reminder
            }
        }

        return when {
            circleNormalized > LATITUDE_NORTH_BOUND -> {
                NormalizedLatitude(
                        latitude = LATITUDE_FULL_RANGE - circleNormalized,
                        flipLongitude = true
                )

            }
            circleNormalized < LATITUDE_SOUTH_BOUND -> {
                NormalizedLatitude(
                        latitude = -LATITUDE_FULL_RANGE - circleNormalized,
                        flipLongitude = true
                )
            }
            else -> {
                NormalizedLatitude(
                        latitude = circleNormalized,
                        flipLongitude = false
                )
            }
        }
    }
}

