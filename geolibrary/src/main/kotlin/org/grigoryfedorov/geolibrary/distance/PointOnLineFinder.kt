package org.grigoryfedorov.geolibrary.distance

import org.grigoryfedorov.geolibrary.Distance
import org.grigoryfedorov.geolibrary.dto.Line
import org.grigoryfedorov.geolibrary.dto.Point

/**
 * Find point on the line with distance from the beginning.
 * null if distance is greater than line length
 */
interface PointOnLineFinder {
    fun findPointOnLineByDistance(line: Line, distance: Distance): Point?
}
