package org.grigoryfedorov.geolibrary

/**
 * Latitudes must always be in the range [-90, 90] (both inclusive), both for input and output.
 */
class LatitudeNormalizer {
    fun normalizeLatitude(latitude: Angle): Angle {
        return if (latitude in LATITUDE_SOUTH_BOUND..LATITUDE_NORTH_BOUND) {
            latitude
        } else {
            // todo
            latitude
        }
    }

}
