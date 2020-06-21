package org.grigoryfedorov.geolibrary.factory

import org.grigoryfedorov.geolibrary.dto.Point
import org.grigoryfedorov.geolibrary.dto.PolyLine
import java.util.*

/**
 * Creates polyline from different collections
 */
class PolyLineFactory {
    fun createPolyLine(points: List<Point>): PolyLine {
        return createFromCollectionCopy(points)
    }

    fun createPolyLine(points: Array<Point>): PolyLine {
        return PolyLine(points.toList())
    }

    fun createPolyLine(points: SortedSet<Point>): PolyLine {
        return createFromCollectionCopy(points)
    }

    private fun createFromCollectionCopy(collection: Collection<Point>): PolyLine {
        return PolyLine(collection.toList())
    }
}
