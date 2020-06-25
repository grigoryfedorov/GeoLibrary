package org.grigoryfedorov.geolibrary.distance

import org.grigoryfedorov.geolibrary.Distance
import org.grigoryfedorov.geolibrary.RIGHT_ANGLE_DEGREES
import org.grigoryfedorov.geolibrary.dto.Point
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

/**
 * Calculates strait distance using spherical coordinates formula
 *
 * See https://en.wikipedia.org/wiki/Spherical_coordinate_system#Distance_in_Spherical_Coordinates
 */
class StraightSphericalDistanceCalculator(
    private val radiusCalculator: RadiusCalculator
) : DistanceCalculator {

    override fun calculateDistance(point1: Point, point2: Point): Distance {
        val r1 = point1.elevation + radiusCalculator.getRadius(latitude = point1.latitude);
        val r2 = point2.elevation + radiusCalculator.getRadius(latitude = point2.latitude);
        val theta1 = Math.toRadians(RIGHT_ANGLE_DEGREES - point1.latitude)
        val theta2 = Math.toRadians(RIGHT_ANGLE_DEGREES - point2.latitude)
        val phi1 = Math.toRadians(point1.longitude)
        val phi2 = Math.toRadians(point2.longitude)

        return sqrt(
            r1.pow(2) + r2.pow(2)
                    - 2 * r1 * r2 * (sin(theta1) * sin(theta2) * cos(abs(phi1 - phi2)) + cos(theta1) * cos(theta2))
        )

    }
}

