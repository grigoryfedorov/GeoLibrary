package org.grigoryfedorov.geolibrary.extentions

import org.grigoryfedorov.geolibrary.dto.Line
import org.grigoryfedorov.geolibrary.dto.PolyLine

fun PolyLine.toLines(): List<Line> {
    return points.zipWithNext { point1, point2 ->
        Line(point1, point2)
    }
}

