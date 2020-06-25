package org.grigoryfedorov.geolibrary.factory

import org.grigoryfedorov.geolibrary.Angle
import org.grigoryfedorov.geolibrary.LATITUDE_NORTH_BOUND
import org.grigoryfedorov.geolibrary.LATITUDE_SOUTH_BOUND
import org.grigoryfedorov.geolibrary.LongitudeNormalizer
import org.grigoryfedorov.geolibrary.Radius
import org.grigoryfedorov.geolibrary.dto.Point
import java.security.InvalidParameterException

/**
 * Factory to create Points and check input params
 */
class PointFactory(
    private val longitudeNormalizer: LongitudeNormalizer
) {
    /**
     * Check input params, normalize them if needed and create Point or throw
     * @throws InvalidParameterException if latitude is out of range -90, 90
     */
    fun createPoint(
        latitude: Angle,
        longitude: Angle,
        elevation: Radius
    ): Point {
        if (latitude !in LATITUDE_SOUTH_BOUND..LATITUDE_NORTH_BOUND) {
            throw InvalidParameterException("Latitudes must always be in the range " +
                    "[$LATITUDE_SOUTH_BOUND, $LATITUDE_NORTH_BOUND] (both inclusive). Got $latitude")
        }

        val normalizedLongitude = longitudeNormalizer.normalizeLongitude(longitude, needFlip = false)

        return Point(
            latitude = latitude,
            longitude = normalizedLongitude,
            elevation = elevation
        )
    }
}
