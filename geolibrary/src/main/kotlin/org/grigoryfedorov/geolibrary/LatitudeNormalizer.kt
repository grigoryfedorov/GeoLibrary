package org.grigoryfedorov.geolibrary

/**
 * Latitudes must always be in the range [-90, 90] (both inclusive), both for input and output.
 *
 * https://stackoverflow.com/a/31125984
 */
class LatitudeNormalizer {
    fun normalizeLatitude(latitude: Angle): NormalizedLatitude {
        if (latitude in LATITUDE_NORTH_BOUND..LATITUDE_NORTH_BOUND) {
            return NormalizedLatitude(
                latitude = latitude,
                flipLongitude = false
            )
        }

        val reminder = latitude.rem(LONGITUDE_FULL_CIRCLE)

        val normalizeLongitude = when {
            reminder > LONGITUDE_EAST_BOUND -> {
                reminder - LONGITUDE_FULL_CIRCLE
            }
            reminder < LONGITUDE_WEST_BOUND -> {
                reminder + LONGITUDE_FULL_CIRCLE
            }
            else -> {
                reminder
            }
        }

        return when {
            normalizeLongitude > LATITUDE_NORTH_BOUND -> {
                NormalizedLatitude(
                        latitude = ANGLE_180_DEGREES - normalizeLongitude,
                        flipLongitude = true
                )

            }
            normalizeLongitude < LATITUDE_SOUTH_BOUND -> {
                NormalizedLatitude(
                        latitude = -ANGLE_180_DEGREES - normalizeLongitude,
                        flipLongitude = true
                )
            }
            else -> {
                NormalizedLatitude(
                        latitude = normalizeLongitude,
                        flipLongitude = false
                )
            }
        }
    }
}


//
//    void Limit_Latitude_Longitude(double *latitude_degrees, double *longitude_degrees) {
//        *latitude_degrees = Limit_Longitude(*latitude_degrees);
//        int flip = 0;
//        if (*latitude_degrees > 90.0) {
//            *latitude_degrees = 180.0 - *latitude_degrees;
//            flip = 1;
//        } else if (*latitude_degrees < -90.0) {
//            *latitude_degrees = -180.0 - *latitude_degrees;
//            flip = 1;
//        }
//        if (flip) {
//            *longitude_degrees += *longitude_degrees > 0 ? -180.0 : 180.0;
//        }
//        *longitude_degrees = Limit_Longitude(*longitude_degrees);
//    }
