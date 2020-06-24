package org.grigoryfedorov.geolibrary.factory

import org.grigoryfedorov.geolibrary.LINE_MAX_SPAN
import org.grigoryfedorov.geolibrary.LatitudeSpanCalculator
import org.grigoryfedorov.geolibrary.LongitudeSpanCalculator
import org.grigoryfedorov.geolibrary.dto.Line
import org.grigoryfedorov.geolibrary.dto.Point
import java.security.InvalidParameterException

class LineFactory(
    private val latitudeSpanCalculator: LatitudeSpanCalculator,
    private val longitudeSpanCalculator: LongitudeSpanCalculator
) {

    fun createLine(point1: Point, point2: Point): Line {
        checkLatitudeSpan(point1, point2)
        checkLongitudeSpan(point1, point2)
        return Line(point1, point2)
    }

    private fun checkLongitudeSpan(
        point1: Point,
        point2: Point
    ) {
        val span = longitudeSpanCalculator.calculateMinLongitudeSpan(
            longitude1 = point1.longitude,
            longitude2 = point2.longitude
        )

        if (span >= LINE_MAX_SPAN) {
            throw InvalidParameterException(
                "Longitude span is too big during line creation. " +
                        "long1 ${point1.longitude} long2 ${point2.longitude} " +
                        "span $span " +
                        "span limit $LINE_MAX_SPAN"
            )
        }
    }

    private fun checkLatitudeSpan(
        point1: Point,
        point2: Point
    ) {
        val span = latitudeSpanCalculator.calculateLatitudeSpan(
            latitude1 = point1.latitude,
            latitude2 = point2.latitude
        )

        if (span >= LINE_MAX_SPAN) {
            throw InvalidParameterException(
                "Latitude span is too big during line creation. " +
                        "lat1 ${point1.latitude} lat2 ${point2.latitude} " +
                        "span $span " +
                        "span limit $LINE_MAX_SPAN"
            )
        }
    }

}