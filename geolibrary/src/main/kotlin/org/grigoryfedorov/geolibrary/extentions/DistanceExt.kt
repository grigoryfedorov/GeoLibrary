package org.grigoryfedorov.geolibrary.extentions

import org.grigoryfedorov.geolibrary.Distance
import org.grigoryfedorov.geolibrary.distance.DistanceCalculator
import org.grigoryfedorov.geolibrary.distance.PointOnLineFinder
import org.grigoryfedorov.geolibrary.dto.Line
import org.grigoryfedorov.geolibrary.dto.Point
import org.grigoryfedorov.geolibrary.dto.PolyLine

fun Line.length(distanceCalculator: DistanceCalculator): Distance {
    return distanceCalculator.calculateDistance(point1, point2)
}

fun PolyLine.getLocationByDistance(
    pointInLineFinder: PointOnLineFinder,
    distance: Distance
): Point? {
    var distanceLeft = distance
    var resultPoint: Point? = null
    for (line in this.toLines()) {
        resultPoint = pointInLineFinder.findPointOnLineByDistance(line, distanceLeft)
        if (resultPoint != null) {
            break
        } else {
            distanceLeft -= distance
        }
    }

    return resultPoint
}
