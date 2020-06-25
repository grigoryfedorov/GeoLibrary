package org.grigoryfedorov.geolibrary

import org.grigoryfedorov.geolibrary.dto.Point
import org.grigoryfedorov.geolibrary.dto.Rectangle

/**
 * Calculates if point in contained withing rectangle.
 * Assumes that rectangle span is less than 180 degrees
 * Rectangle's borders are actually meridians and parallels
 * so for big rectangles borders are not strait and angles are not right
 */
class ContainedInRectangleCalculator {

    /**
     * Determine if a Point is contained within the rectangle
     * (elevation is disregarded in this case).
     * If location is on the border it considered included.
     * Borders of Rectangle are parallel to meridians and parallels,
     * they are not straight, especially for large rectangles
     *
     * @param rectangle Rectangle to determine if Point is inside
     * @param location Point to determine if it is contained in rectangle or not
     * @return true if location is contained within the rectangle or false otherwise
     */
    fun isPointContainedInRectangle(rectangle: Rectangle, location: Point): Boolean {
        val south = rectangle.southWest.latitude
        val north = rectangle.northEast.latitude

        val west = rectangle.southWest.longitude
        val east = rectangle.northEast.longitude

        val isInLatitude = location.latitude in south..north

        val isInLongitude = if (east > west) {
            location.longitude in west..east
        } else {
            location.longitude in west..LONGITUDE_EAST_BOUND
                    || location.longitude in LONGITUDE_WEST_BOUND..east
        }

        return isInLatitude && isInLongitude
    }
}
