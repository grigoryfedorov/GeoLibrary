package org.grigoryfedorov.geolibrary.distance

import org.grigoryfedorov.geolibrary.Angle
import org.grigoryfedorov.geolibrary.Radius

/**
 * Calculates Earth radius for specific latitude
 */
interface RadiusCalculator {
    fun getRadius(latitude: Angle): Radius
}

