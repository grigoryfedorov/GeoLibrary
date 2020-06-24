package org.grigoryfedorov.geolibrary.factory

import org.grigoryfedorov.geolibrary.POLY_LINE_MIN_POINTS_COUNT
import org.grigoryfedorov.geolibrary.dto.Point
import org.grigoryfedorov.geolibrary.dto.PolyLine
import java.security.InvalidParameterException
import java.util.*

/**
 * Creates polyline from different collections
 */
class PolyLineFactory {
    fun createPolyLine(points: List<Point>): PolyLine {
        return createFromCollectionCopy(points)
    }

    fun createPolyLine(points: Array<Point>): PolyLine {
        checkInputPointsCount(points.size)
        return PolyLine(points.toList())
    }

    fun createPolyLine(points: SortedSet<Point>): PolyLine {
        return createFromCollectionCopy(points)
    }

    private fun createFromCollectionCopy(collection: Collection<Point>): PolyLine {
        checkInputPointsCount(collection.size)
        return PolyLine(collection.toList())
    }

    private fun checkInputPointsCount(count: Int) {
        if (count < POLY_LINE_MIN_POINTS_COUNT) {
            throw InvalidParameterException(
                "Could not create poly line from less than $POLY_LINE_MIN_POINTS_COUNT points. " +
                        "Got $count points"
            )
        }
    }
}
