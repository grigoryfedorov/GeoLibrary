package org.grigoryfedorov.geolibrary.distance

import org.grigoryfedorov.geolibrary.Angle
import org.grigoryfedorov.geolibrary.Radius

/**
 * Calculates Earth radius for specific latitude
 */
interface RadiusCalculator {

    /**
     * Get or calculate Earth radius for latitude
     *
     * @param latitude latitude in degrees
     * @return Earth radius in meters
     */
    fun getRadius(latitude: Angle): Radius
}

