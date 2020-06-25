package org.grigoryfedorov.geolibrary.dto

import org.grigoryfedorov.geolibrary.Angle
import org.grigoryfedorov.geolibrary.DEFAULT_POINT_TRANSLATOR
import org.grigoryfedorov.geolibrary.PointTranslator
import org.grigoryfedorov.geolibrary.Radius

/**
 * Represents any location on (or above) Earth.
 *
 * @property latitude WGS84 latitude in degrees in range -90, 90 (both inclusive)
 * @property longitude WGS84 longitude in degrees in range -180, 180 (left inclusive, right exclusive).
 * @property elevation distance from WGS84 ellipsoid in meters, not restrictions to range,
 * but large elevation can mage useless such calculations as distance between points
 * @constructor use [org.grigoryfedorov.geolibrary.factory.PointFactory] to create Point
 */
class Point internal constructor(
    val latitude: Angle,
    val longitude: Angle,
    val elevation: Radius
) {

    /**
     * Translate Point: vector directions are added to coordinates and normalized
     *
     * @param vector to make translation
     * @param pointTranslator default or custom translator, which implements math
     * @return translated copy on Point
     */
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

