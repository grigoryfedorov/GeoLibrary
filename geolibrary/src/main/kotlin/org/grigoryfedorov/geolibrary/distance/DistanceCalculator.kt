package org.grigoryfedorov.geolibrary.distance

import org.grigoryfedorov.geolibrary.Distance
import org.grigoryfedorov.geolibrary.dto.Point

/**
 * Calculates distance between to points.
 */
interface DistanceCalculator {
    fun calculateDistance(point1: Point, point2: Point): Distance
}
