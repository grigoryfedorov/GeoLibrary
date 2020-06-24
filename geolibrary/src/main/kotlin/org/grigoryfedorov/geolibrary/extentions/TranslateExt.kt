package org.grigoryfedorov.geolibrary.extentions

import org.grigoryfedorov.geolibrary.LatitudeNormalizer
import org.grigoryfedorov.geolibrary.LongitudeNormalizer
import org.grigoryfedorov.geolibrary.PointTranslator
import org.grigoryfedorov.geolibrary.dto.Line
import org.grigoryfedorov.geolibrary.dto.Point
import org.grigoryfedorov.geolibrary.dto.PolyLine
import org.grigoryfedorov.geolibrary.dto.Rectangle
import org.grigoryfedorov.geolibrary.dto.Vector


fun Point.translate(vector: Vector): Point {
    val pointTranslator = PointTranslator(LatitudeNormalizer(), LongitudeNormalizer())
    return pointTranslator.translate(this, vector)
}

fun Line.translate(vector: Vector): Line {
    return Line(
            point1 = point1.translate(vector),
            point2 = point2.translate(vector)
    )
}

fun PolyLine.translate(vector: Vector): PolyLine {
    return PolyLine(
            points = points.map {
                it.translate(vector)
            }
    )
}

fun Rectangle.translate(vector: Vector): Rectangle {
    return Rectangle(
            southWest = southWest.translate(vector),
            northEast = northEast.translate(vector)
    )
}
