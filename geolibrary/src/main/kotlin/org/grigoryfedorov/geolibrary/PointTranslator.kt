package org.grigoryfedorov.geolibrary

import org.grigoryfedorov.geolibrary.dto.Point
import org.grigoryfedorov.geolibrary.dto.Vector

/**
 *  https://stackoverflow.com/a/31125984
 */
class PointTranslator(
    private val latitudeNormalizer: LatitudeNormalizer,
    private val longitudeNormalizer: LongitudeNormalizer
) {
    fun translate(point: Point, vector: Vector): Point {
        val translatedLatitude = point.latitude + vector.northbound
        val translatedLongitude = point.longitude + vector.eastbound
        val translatedElevation = point.elevation + vector.elevationBound

        val normalizedLatitude = latitudeNormalizer.normalizeLatitude(translatedLatitude)
        val normalizedLongitude = longitudeNormalizer.normalizeLongitude(
            longitude = translatedLongitude,
            needFlip = normalizedLatitude.flipLongitude
        )

        return Point(
            latitude = normalizedLatitude.latitude,
            longitude = normalizedLongitude,
            elevation = translatedElevation
        )
    }
}
