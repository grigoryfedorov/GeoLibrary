package org.grigoryfedorov.geolibrary.dto

import org.grigoryfedorov.geolibrary.DEFAULT_DISTANCE_CALCULATOR
import org.grigoryfedorov.geolibrary.DEFAULT_POINT_TRANSLATOR
import org.grigoryfedorov.geolibrary.Distance
import org.grigoryfedorov.geolibrary.PointTranslator
import org.grigoryfedorov.geolibrary.distance.DistanceCalculator

/**
 * Represents a straight line between 2 Points
 *
 * @property point1 first point, line has no direction
 * @property point2 second point, line has no direction
 *
 * @constructor use [org.grigoryfedorov.geolibrary.factory.LineFactory] to create Line
 */
class Line internal constructor(
    val point1: Point,
    val point2: Point
) {

    /**
     * Exposes length (measured as a straight line).
     * To simplify the math, distance calculations need only be accurate at short distances
     * and need not be accurate within 5 degrees of the poles.
     *
     * @param distanceCalculator default or custom implementation of line distance calculation
     */
    fun length(distanceCalculator: DistanceCalculator = DEFAULT_DISTANCE_CALCULATOR): Distance {
        return distanceCalculator.calculateDistance(point1, point2)
    }

    /**
     * Translate line via translating points: vector directions are added to coordinates and normalized
     * It is important, that geometrical size may change, because of coordinate system
     *
     * @param vector to make translation
     * @param pointTranslator default or custom translator, which implements math
     * @return translated copy on Line
     */
    fun translate(
        vector: Vector,
        pointTranslator: PointTranslator = DEFAULT_POINT_TRANSLATOR
    ): Line {
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
