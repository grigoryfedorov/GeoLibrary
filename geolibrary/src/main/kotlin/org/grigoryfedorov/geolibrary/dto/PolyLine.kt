package org.grigoryfedorov.geolibrary.dto

/**
 * Represents a segmented line between 2 or more Points
 * Constructed from an ordered collection of 2 or more Points.
 * Exposes a function to get a 3D location anywhere along the PolyLine
 * at a given a distance measured from the origin of the PolyLine.
 * Exposes a way to get the PolyLine as a list of Lines.
 */
class PolyLine internal constructor(
    val points: Collection<Point>
) {

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
