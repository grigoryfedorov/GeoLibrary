package org.grigoryfedorov.geolibrary.distance

import org.grigoryfedorov.geolibrary.Distance
import org.grigoryfedorov.geolibrary.LATITUDE_DEGREE_METERS
import org.grigoryfedorov.geolibrary.dto.Line
import org.grigoryfedorov.geolibrary.dto.Point
import kotlin.math.cos
import kotlin.math.sqrt

/**
 * Use simplified calculations for distance. Suitable for short distances
 *
 * TODO: refactor [calculateDistance] and [findPointOnLineByDistance] extract shared logic
 */
class SimpleStraitLineCalculator : DistanceCalculator, PointOnLineFinder {
    override fun calculateDistance(point1: Point, point2: Point): Distance {
        val lat = (point1.latitude + point2.latitude) / 2
        val dx = LATITUDE_DEGREE_METERS * cos(Math.toRadians(lat)) * (point1.longitude - point2.longitude)
        val dy = LATITUDE_DEGREE_METERS * (point1.latitude - point2.latitude)

        val d = sqrt(dx * dx + dy * dy)

        val elevationDiff = point1.elevation - point2.elevation

        return sqrt(d*d + elevationDiff*elevationDiff)
    }

    override fun findPointOnLineByDistance(line: Line, distance: Distance): Point? {
        val lat = (line.point1.latitude + line.point2.latitude) / 2
        val dx = LATITUDE_DEGREE_METERS * cos(Math.toRadians(lat)) * (line.point1.longitude - line.point2.longitude)
        val dy = LATITUDE_DEGREE_METERS * (line.point1.latitude - line.point2.latitude)

        val d = sqrt(dx * dx + dy * dy)

        val elevationDiff = line.point1.elevation - line.point2.elevation

        val deltaWithElevation = sqrt(d*d + elevationDiff*elevationDiff)

        if (distance > deltaWithElevation) {
            return null
        }

        val elevation = elevationDiff * distance / deltaWithElevation

        val distanceWithoutElevation = d * distance / deltaWithElevation

        val dxt = if (d == 0.0) 0.0 else dx * distanceWithoutElevation / d
        val dyt = if (d == 0.0) 0.0 else dy * distanceWithoutElevation / d

        val latT = dyt / LATITUDE_DEGREE_METERS + line.point1.latitude
        val lonT = dxt /  LATITUDE_DEGREE_METERS * cos(Math.toRadians(lat)) + line.point1.longitude

        return Point(latT, lonT, elevation)
    }
}
