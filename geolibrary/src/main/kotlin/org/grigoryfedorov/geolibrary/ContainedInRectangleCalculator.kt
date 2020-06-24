package org.grigoryfedorov.geolibrary

import org.grigoryfedorov.geolibrary.dto.Point
import org.grigoryfedorov.geolibrary.dto.Rectangle

/**
 * Calculates if point in contained withing rectangle.
 * Assumes that rectangle span is less than 180 degrees
 * Rectangle's borders are actually meridians and parallels
 * so for big rectangles borders are not strait and angles are not right
 * Also its impossible to create rectangle across the pole
 */
class ContainedInRectangleCalculator {
    fun isPointContainedInRectangle(rectangle: Rectangle, point: Point): Boolean {
        val south = rectangle.southWest.latitude
        val north = rectangle.northEast.latitude

        val west = rectangle.southWest.longitude
        val east = rectangle.northEast.longitude

        val isInLatitude = point.latitude in south..north

        val isInLongitude = if (east > west) {
            point.longitude in west..east
        } else {
            point.longitude in west..LONGITUDE_EAST_BOUND
                    || point.longitude in LONGITUDE_WEST_BOUND..east
        }

        return isInLatitude && isInLongitude
    }
}
