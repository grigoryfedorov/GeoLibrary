package org.grigoryfedorov.geolibrary.extentions

import org.grigoryfedorov.geolibrary.ContainedInRectangleCalculator
import org.grigoryfedorov.geolibrary.dto.Point
import org.grigoryfedorov.geolibrary.dto.Rectangle


/**
 * Exposes a way to determine if a Point is contained within the rectangle (elevation is
disregarded in this case).
 */
fun Rectangle.contains(
    location: Point,
    containedInRectangleCalculator: ContainedInRectangleCalculator
): Boolean {
    return containedInRectangleCalculator.isPointContainedInRectangle(this, location)
}
