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

    /**
     * Create PolyLine from list of Points or throw
     * List is copied
     *
     * @throws InvalidParameterException if points list is less than two Points
     */
    fun createPolyLine(points: List<Point>): PolyLine {
        return createFromCollectionCopy(points)
    }

    /**
     * Create PolyLine from array of Points or throw
     * Array is copied
     *
     * @throws InvalidParameterException if points array is less than two Points
     */
    fun createPolyLine(points: Array<Point>): PolyLine {
        checkInputPointsCount(points.size)
        return PolyLine(points.toList())
    }

    /**
     * Create PolyLine from ordered set of Points or throw
     * Set is copied
     *
     * @throws InvalidParameterException if points set is less than two Points
     */
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
