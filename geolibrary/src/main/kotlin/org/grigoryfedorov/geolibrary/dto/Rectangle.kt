package org.grigoryfedorov.geolibrary.dto

import org.grigoryfedorov.geolibrary.ContainedInRectangleCalculator
import org.grigoryfedorov.geolibrary.DEFAULT_CONTAINED_IN_RECTANGLE_CALCULATOR
import org.grigoryfedorov.geolibrary.DEFAULT_POINT_TRANSLATOR
import org.grigoryfedorov.geolibrary.PointTranslator

/**
 * Represents a rectangular area, defined by 2 corner Points.
 * Rectangles and Lines only need to support a span of less than 180 degrees longitude and latitude.
 * Constructed from 2 corner Points(South/West, North/East).
 * Shape may not be geometrically correct because of Earth coordinate system:
 * borders of Rectangle are parallel to meridians and parallels,
 * they are not even straight, especially for large rectangles
 * Also its impossible to create rectangle across the pole
 *
 * @property southWest South/West corner Point
 * @property northEast North/East corner Point
 * @constructor use [org.grigoryfedorov.geolibrary.factory.RectangleFactory] to create Rectangle
 */
class Rectangle internal constructor(
    val southWest: Point,
    val northEast: Point
) {

    /**
     * Determine if a Point is contained within the rectangle
     * (elevation is disregarded in this case).
     * If location is on the border it considered included.
     * Borders of Rectangle are parallel to meridians and parallels,
     * they are not straight, especially for large rectangles
     *
     * @param location Point to determine if it is contained in rectangle or not
     * @param containedInRectangleCalculator default or custom implementation of calculations
     * @return true if location is contained within the rectangle or false otherwise
     */
    fun contains(
        location: Point,
        containedInRectangleCalculator: ContainedInRectangleCalculator = DEFAULT_CONTAINED_IN_RECTANGLE_CALCULATOR
    ): Boolean {
        return containedInRectangleCalculator.isPointContainedInRectangle(this, location)
    }

    /**
     * Translate PolyLine by it's Points: vector directions are added to coordinates and normalized.
     * It is important, that geometrical size and shape may change, because of coordinate system
     *
     * @param vector to make translation
     * @param pointTranslator default or custom translator, which implements math
     * @return translated copy on PolyLine
     */
    fun translate(
        vector: Vector,
        pointTranslator: PointTranslator = DEFAULT_POINT_TRANSLATOR
    ): Rectangle {
        return Rectangle(
            southWest = southWest.translate(vector, pointTranslator),
            northEast = northEast.translate(vector, pointTranslator)
        )
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Rectangle

        if (southWest != other.southWest) return false
        if (northEast != other.northEast) return false

        return true
    }

    override fun hashCode(): Int {
        var result = southWest.hashCode()
        result = 31 * result + northEast.hashCode()
        return result
    }

    override fun toString(): String {
        return "Rectangle(southWest=$southWest, northEast=$northEast)"
    }

}
