package org.grigoryfedorov.geolibrary.factory

import org.grigoryfedorov.geolibrary.Angle
import org.grigoryfedorov.geolibrary.LATITUDE_NORTH_BOUND
import org.grigoryfedorov.geolibrary.LATITUDE_SOUTH_BOUND
import org.grigoryfedorov.geolibrary.LongitudeNormalizer
import org.grigoryfedorov.geolibrary.Radius
import org.grigoryfedorov.geolibrary.dto.Point
import org.grigoryfedorov.geolibrary.exception.InvalidLatitudeParameterException

/**
 * Latitudes must always be in the range [-90, 90] (both inclusive), both for input and output.
 * Latitudes outside this range are considered an error
 * Longitudes passed to this library (input) may be outside this range and are automatically
 * wrapped to the range by the library.
 * Public functions are robust against incorrect input parameters. Incorrect input parameters
 * throw an InvalidParameterException.
 * Longitudes returned by this library (output) are always in the range [-180, 180> (left
 * inclusive, right exclusive).
 * Longitudes passed to this library (input) may be outside this range and are automatically
 * wrapped to the range by the library.
 */
class PointFactory(
    private val longitudeNormalizer: LongitudeNormalizer
) {
    fun createPoint(
        latitude: Angle,
        longitude: Angle,
        elevation: Radius
    ): Point {
        if (latitude !in LATITUDE_SOUTH_BOUND..LATITUDE_NORTH_BOUND) {
            throw InvalidLatitudeParameterException("Latitudes must always be in the range " +
                    "[$LATITUDE_SOUTH_BOUND, $LATITUDE_NORTH_BOUND] (both inclusive). Got $latitude")
        }

        val normalizedLongitude = longitudeNormalizer.normalizeLongitude(longitude)

        return Point(
            latitude = latitude,
            longitude = normalizedLongitude,
            elevation = elevation
        )
    }
}
