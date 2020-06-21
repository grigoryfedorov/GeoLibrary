package org.grigoryfedorov.geolibrary

import org.grigoryfedorov.geolibrary.distance.DistanceCalculator
import org.grigoryfedorov.geolibrary.distance.PointOnLineFinder
import org.grigoryfedorov.geolibrary.factory.PointFactory
import org.grigoryfedorov.geolibrary.factory.PolyLineFactory

/**
 * Should be main library class with configurations and factories
 */
class GeoLibrary {
    var distanceCalculator: DistanceCalculator = DEFAULT_DISTANCE_CALCULATOR
    var pointOnLineFinder: PointOnLineFinder = DEFAULT_POINT_ON_LINE_FINDER

    fun createDefaultPointFactory(): PointFactory {
        val longitudeNormalizer = LongitudeNormalizer()
        return PointFactory(longitudeNormalizer)
    }

    fun createDefaultPolyLineFactory(): PolyLineFactory {
        return PolyLineFactory()
    }
}
