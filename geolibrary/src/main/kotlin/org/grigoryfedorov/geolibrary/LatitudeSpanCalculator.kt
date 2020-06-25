package org.grigoryfedorov.geolibrary

import kotlin.math.abs

/**
 * Calculate span (delta) in degrees for two latitudes.
 * Assume that both latitudes are already normalized in range
 * Span is always positive
 */
class LatitudeSpanCalculator {
    /**
     * Calculate latitude span in degrees
     * @param latitude1 first latitude (order is not important)
     * @param latitude2 second latitude (order is not important)
     * @return span (delta) in degrees
     */
    fun calculateLatitudeSpan(latitude1: Angle, latitude2: Angle): Angle {
        return abs(latitude1 - latitude2)
    }
}
