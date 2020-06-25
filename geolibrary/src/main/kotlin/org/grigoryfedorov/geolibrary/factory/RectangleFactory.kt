package org.grigoryfedorov.geolibrary.factory

import org.grigoryfedorov.geolibrary.Angle
import org.grigoryfedorov.geolibrary.LatitudeSpanCalculator
import org.grigoryfedorov.geolibrary.LongitudeSpanCalculator
import org.grigoryfedorov.geolibrary.RECTANGLE_MAX_SPAN
import org.grigoryfedorov.geolibrary.dto.Point
import org.grigoryfedorov.geolibrary.dto.Rectangle
import java.security.InvalidParameterException

/**
 * Factory to create rectangle with input params check
 */
class RectangleFactory(
    private val latitudeSpanCalculator: LatitudeSpanCalculator,
    private val longitudeSpanCalculator: LongitudeSpanCalculator
) {

    /**
     * Create rectangle from corner Points or throw
     *
     * @throws InvalidParameterException if South is greater than North or span is too big.
     * @see [RECTANGLE_MAX_SPAN] for current line span limit.
     * Span is delta between latitude and longitude independently
     */
    fun createRectangle(southWest: Point, northEast: Point): Rectangle {
        checkLatitudeOrientation(
            south = southWest.latitude,
            north = northEast.latitude
        )
        checkLatitudeSpan(
            south = southWest.latitude,
            north = northEast.latitude
        )
        checkLongitudeSpan(
            west = southWest.longitude,
            east = northEast.longitude
        )

        return Rectangle(
            southWest = southWest,
            northEast = northEast
        )
    }

    private fun checkLatitudeOrientation(
        south: Angle,
        north: Angle
    ) {
        if (south > north) {
            throw InvalidParameterException(
                "South bound latitude is greater than north during rectangle creation. " +
                        "South $south north $north"
            )
        }
    }

    private fun checkLatitudeSpan(
        south: Angle,
        north: Angle
    ) {
        val span = latitudeSpanCalculator.calculateLatitudeSpan(
            latitude1 = south,
            latitude2 = north
        )

        if (span >= RECTANGLE_MAX_SPAN) {
            throw InvalidParameterException(
                "Latitude span is too big during rectangle creation. " +
                        "South $south north $north " +
                        "span $span " +
                        "span limit $RECTANGLE_MAX_SPAN"
            )
        }
    }

    private fun checkLongitudeSpan(west: Angle, east: Angle) {
        val span = longitudeSpanCalculator.calculateOrientedLongitudeSpan(
            west = west,
            east = east
        )

        if (span >= RECTANGLE_MAX_SPAN) {
            throw InvalidParameterException(
                "Longitude span is too big during rectangle creation. " +
                        "West $west east $east " +
                        "span $span " +
                        "span limit $RECTANGLE_MAX_SPAN"
            )
        }
    }
}
