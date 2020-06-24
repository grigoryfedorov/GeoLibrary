package org.grigoryfedorov.geolibrary.dto

import org.grigoryfedorov.geolibrary.ContainedInRectangleCalculator
import org.grigoryfedorov.geolibrary.PointTranslator

/**
 * Represents a rectangular area, defined by 2 corner Points.
 * Rectangles and Lines only need to support a span of less than 180 degrees longitude and latitude.
 * Constructed from 2 corner Points(South/West, North/East).
 * Exposes a way to determine if a Point is contained within the rectangle (elevation is
disregarded in this case).
 */
class Rectangle internal constructor(
    val southWest: Point,
    val northEast: Point
) {

    /**
     * Exposes a way to determine if a Point is contained within the rectangle (elevation is
     * disregarded in this case).
     */
    fun contains(
        location: Point,
        containedInRectangleCalculator: ContainedInRectangleCalculator
    ): Boolean {
        return containedInRectangleCalculator.isPointContainedInRectangle(this, location)
    }


    fun translate(vector: Vector, pointTranslator: PointTranslator): Rectangle {
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
