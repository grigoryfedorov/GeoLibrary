package org.grigoryfedorov.geolibrary.dto

import org.grigoryfedorov.geolibrary.DEFAULT_DISTANCE_CALCULATOR
import org.grigoryfedorov.geolibrary.DEFAULT_POINT_ON_LINE_FINDER
import org.grigoryfedorov.geolibrary.DEFAULT_POINT_TRANSLATOR
import org.grigoryfedorov.geolibrary.Distance
import org.grigoryfedorov.geolibrary.PointTranslator
import org.grigoryfedorov.geolibrary.distance.DistanceCalculator
import org.grigoryfedorov.geolibrary.distance.PointOnLineFinder
import java.security.InvalidParameterException

/**
 * Represents a segmented line between 2 or more Points
 *
 * @property points - ordered collection of 2 or more Points
 * @constructor use [org.grigoryfedorov.geolibrary.factory.PolyLineFactory] to create PolyLine
 */
class PolyLine internal constructor(
    val points: Collection<Point>
) {

    /**
     * Get PolyLine as a list of lines
     *
     * @return list of PolyLine segments as Line
     */
    fun toLines(): List<Line> {
        return points.zipWithNext { point1, point2 ->
            Line(point1, point2)
        }
    }

    /**
     * Get a 3D location anywhere along the PolyLine
     * at a given a distance measured from the origin of the PolyLine.
     *
     * @param distance from origin of the PolyLine to searched locations, in meters
     * @param pointInLineFinder default or custom implementations of point on line calculation
     * @param distanceCalculator default or custom implementation of line distance calculation
     * @return valid point if distance is less than PolyLine distance or null if point is outside the PolyLine
     */
    fun getLocationByDistance(
        distance: Distance,
        pointInLineFinder: PointOnLineFinder = DEFAULT_POINT_ON_LINE_FINDER,
        distanceCalculator: DistanceCalculator = DEFAULT_DISTANCE_CALCULATOR
    ): Point? {
        if (distance < 0) {
            throw InvalidParameterException("Distance is less when zero $distance")
        }

        var distanceLeft = distance
        var resultPoint: Point? = null
        for (line in this.toLines()) {
            resultPoint = pointInLineFinder.findPointOnLineByDistance(line, distanceLeft)
            if (resultPoint != null) {
                break
            } else {
                distanceLeft -= line.length(distanceCalculator)
            }
        }

        return resultPoint
    }

    /**
     * Translate PolyLine by it's Points: vector directions are added to coordinates and normalized
     *
     * @param vector to make translation
     * @param pointTranslator default or custom translator, which implements math
     * @return translated copy on PolyLine
     */
    fun translate(
        vector: Vector,
        pointTranslator: PointTranslator = DEFAULT_POINT_TRANSLATOR
    ): PolyLine {
        return PolyLine(
            points = points.map {
                it.translate(vector, pointTranslator)
            }
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PolyLine

        if (points != other.points) return false

        return true
    }

    override fun hashCode(): Int {
        return points.hashCode()
    }

    override fun toString(): String {
        return "PolyLine(points=$points)"
    }

}
