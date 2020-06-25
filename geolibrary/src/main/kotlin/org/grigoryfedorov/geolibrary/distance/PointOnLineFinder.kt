package org.grigoryfedorov.geolibrary.distance

import org.grigoryfedorov.geolibrary.Distance
import org.grigoryfedorov.geolibrary.dto.Line
import org.grigoryfedorov.geolibrary.dto.Point

/**
 * Finds point on the line
 */
interface PointOnLineFinder {

    /**
     * Get a 3D location anywhere along the Line
     * at a given a distance measured from the origin of the Line.
     *
     * @param line Line to search for location
     * @param distance from origin of the Line to searched locations, in meters
     * @return valid point if distance is less than Line distance or null if point is outside the Line
     */
    fun findPointOnLineByDistance(line: Line, distance: Distance): Point?
}
