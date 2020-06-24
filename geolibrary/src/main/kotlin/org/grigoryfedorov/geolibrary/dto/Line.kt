package org.grigoryfedorov.geolibrary.dto

import org.grigoryfedorov.geolibrary.Distance
import org.grigoryfedorov.geolibrary.PointTranslator
import org.grigoryfedorov.geolibrary.distance.DistanceCalculator

/**
 * Represents a straight line between 2 Points
 * Constructed from any 2 Points.
 * Exposes its length (measured as a straight line).
 *
 * To simplify the math, distance calculations need only be accurate at short distances
 * and need not be accurate within 5 degrees of the poles.
 *
 * Rectangles and Lines only need to support a span of less than 180 degrees longitude and latitude.
 */
class Line internal constructor(
    val point1: Point,
    val point2: Point
) {

    fun length(distanceCalculator: DistanceCalculator): Distance {
        return distanceCalculator.calculateDistance(point1, point2)
    }

    fun translate(vector: Vector, pointTranslator: PointTranslator): Line {
        return Line(
            point1 = point1.translate(vector, pointTranslator),
            point2 = point2.translate(vector, pointTranslator)
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Line

        if (point1 != other.point1) return false
        if (point2 != other.point2) return false

        return true
    }

    override fun hashCode(): Int {
        var result = point1.hashCode()
        result = 31 * result + point2.hashCode()
        return result
    }

    override fun toString(): String {
        return "Line(point1=$point1, point2=$point2)"
    }
}
