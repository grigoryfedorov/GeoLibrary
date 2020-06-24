package org.grigoryfedorov.geolibrary

import kotlin.math.abs

/**
 * Calculate span (delta) in degrees for two latitudes.
 * Assume that both latitudes are already normalized in range
 * Span is always positive
 */
class LatitudeSpanCalculator {
    fun calculateLatitudeSpan(latitude1: Angle, latitude2: Angle): Angle {
        return abs(latitude1 - latitude2)
    }
}