package org.grigoryfedorov.geolibrary.dto

import org.grigoryfedorov.geolibrary.Angle
import org.grigoryfedorov.geolibrary.DEFAULT_POINT_TRANSLATOR
import org.grigoryfedorov.geolibrary.PointTranslator
import org.grigoryfedorov.geolibrary.Radius

/**
 * Represents any location on (or above) Earth.
 * Constructed with a latitude, longitude and elevation (distance from WGS84 ellipsoid).
 * All coordinates are expressed as WGS84 latitude and longitude, in degrees
 * Latitudes must always be in the range [-90, 90] (both inclusive), both for input and output.
 * Latitudes outside this range are considered an error
 * Longitudes passed to this library (input) may be outside this range and are automatically
 * wrapped to the range by the library.
 */
class Point internal constructor(
    val latitude: Angle,
    val longitude: Angle,
    val elevation: Radius
) {

    fun translate(
        vector: Vector,
        pointTranslator: PointTranslator = DEFAULT_POINT_TRANSLATOR
    ): Point {
        return pointTranslator.translate(this, vector)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Point

        if (latitude != other.latitude) return false
        if (longitude != other.longitude) return false
        if (elevation != other.elevation) return false

        return true
    }

    override fun hashCode(): Int {
        var result = latitude.hashCode()
        result = 31 * result + longitude.hashCode()
        result = 31 * result + elevation.hashCode()
        return result
    }

    override fun toString(): String {
        return "Point(latitude=$latitude, longitude=$longitude, elevation=$elevation)"
    }
}

