package org.grigoryfedorov.geolibrary.factory

import org.grigoryfedorov.geolibrary.ANGLE_180_DEGREES
import org.grigoryfedorov.geolibrary.ANGLE_360_DEGREES
import org.grigoryfedorov.geolibrary.dto.Point
import org.grigoryfedorov.geolibrary.dto.Rectangle
import java.security.InvalidParameterException
import kotlin.math.abs

/**
 * Factory to create rectangle with input params check
 */
class RectangleFactory {
    fun createRectangle(southWest: Point, northEast: Point): Rectangle {
        checkLatitudeOrThrow(southWest, northEast)
        checkLongitudeOrThrow(northEast, southWest)

        return Rectangle(
            southWest = southWest,
            northEast = northEast
        )
    }

    private fun checkLongitudeOrThrow(
        northEast: Point,
        southWest: Point
    ) {
        val longitudeSpan = if (northEast.longitude < 0 && southWest.longitude > 0) {
            ANGLE_360_DEGREES - abs(northEast.longitude) - southWest.longitude
        } else {
            northEast.longitude - southWest.longitude
        }

        if (longitudeSpan >= ANGLE_180_DEGREES) {
            throw InvalidParameterException(
                "Longitude span is too big during rectangle creation. " +
                        "West lon ${southWest.longitude} east lon ${northEast.longitude} " +
                        "span $longitudeSpan " +
                        "span limit $ANGLE_180_DEGREES"
            )
        }
    }

    private fun checkLatitudeOrThrow(
        southWest: Point,
        northEast: Point
    ) {
        if (southWest.latitude > northEast.latitude) {
            throw InvalidParameterException(
                "South bound latitude is greater than north during rectangle creation. " +
                        "South lat ${southWest.latitude} north lat ${northEast.latitude}"
            )
        }

        val latitudeSpan = northEast.latitude - southWest.latitude
        if (latitudeSpan >= ANGLE_180_DEGREES) {
            throw InvalidParameterException(
                "Latitude span is too big during rectangle creation. " +
                        "South lat ${southWest.latitude} north lat ${northEast.latitude} " +
                        "span $latitudeSpan " +
                        "span limit $ANGLE_180_DEGREES"
            )
        }
    }
}
