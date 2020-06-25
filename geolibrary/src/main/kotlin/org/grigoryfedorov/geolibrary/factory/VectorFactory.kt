package org.grigoryfedorov.geolibrary.factory

import org.grigoryfedorov.geolibrary.Angle
import org.grigoryfedorov.geolibrary.Radius
import org.grigoryfedorov.geolibrary.dto.Vector

/**
 * Creates vectors
 *
 * Decided not to normalize vector lat and lon,
 * they would be normalized after translation if needed
 */
class VectorFactory {

    /**
     * Create Vector from directions, no checks made, does not throw
     */
    fun createVector(
        latitude: Angle,
        longitude: Angle,
        elevation: Radius
    ): Vector {
        return Vector(latitude, longitude, elevation)
    }
}
