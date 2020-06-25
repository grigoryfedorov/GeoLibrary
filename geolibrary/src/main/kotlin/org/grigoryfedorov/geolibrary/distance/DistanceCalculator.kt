package org.grigoryfedorov.geolibrary.distance

import org.grigoryfedorov.geolibrary.Distance
import org.grigoryfedorov.geolibrary.dto.Point

/**
 * Calculates distance between two points.
 */
interface DistanceCalculator {
    /**
     * Calculates distance between two Points
     *
     * @param point1 first Point, order is not important
     * @param point2 second Point, order is not important
     * @return distance in meters
     */
    fun calculateDistance(point1: Point, point2: Point): Distance
}
