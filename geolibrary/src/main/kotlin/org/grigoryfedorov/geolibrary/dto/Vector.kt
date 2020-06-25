package org.grigoryfedorov.geolibrary.dto

import org.grigoryfedorov.geolibrary.Angle
import org.grigoryfedorov.geolibrary.Radius

/**
 * Represents a 3D shift for translation of other 3D objects.
 *
 * Constructed from 3 distances, northbound and eastbound (specified in degrees),
 * and a distance that specifies the change in elevation.
 * The purpose of Vector is to be able to get translated instances of Points, Lines, PolyLines and
 * Rectangles.
 *
 * @property northbound latitude translation towards North in degrees
 * @property eastbound longitude translation towards East in degrees
 * @property elevationBound elevation change, positive above Earth surface, negative - below
 *
 * @constructor use [org.grigoryfedorov.geolibrary.factory.VectorFactory] to create Vector
 */
class Vector internal constructor(
    val northbound: Angle,
    val eastbound: Angle,
    val elevationBound: Radius
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Vector

        if (northbound != other.northbound) return false
        if (eastbound != other.eastbound) return false
        if (elevationBound != other.elevationBound) return false

        return true
    }

    override fun hashCode(): Int {
        var result = northbound.hashCode()
        result = 31 * result + eastbound.hashCode()
        result = 31 * result + elevationBound.hashCode()
        return result
    }

    override fun toString(): String {
        return "Vector(northbound=$northbound, eastbound=$eastbound, elevationBound=$elevationBound)"
    }
}
