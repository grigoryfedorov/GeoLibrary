package org.grigoryfedorov.geolibrary

import org.grigoryfedorov.geolibrary.factory.LineFactory
import org.grigoryfedorov.geolibrary.factory.PointFactory
import org.grigoryfedorov.geolibrary.factory.PolyLineFactory
import org.grigoryfedorov.geolibrary.factory.RectangleFactory

/**
 * Main library class, creates factories.
 */
class GeoLibrary(
    private val latitudeSpanCalculator: LatitudeSpanCalculator = DEFAULT_LATITUDE_SPAN_CALCULATOR,
    private val longitudeSpanCalculator: LongitudeSpanCalculator = DEFAULT_LONGITUDE_SPAN_CALCULATOR,
    private val longitudeNormalizer: LongitudeNormalizer = DEFAULT_LONGITUDE_NORMALIZER
) {

    fun createPointFactory(): PointFactory {
        return PointFactory(longitudeNormalizer)
    }

    fun createPolyLineFactory(): PolyLineFactory {
        return PolyLineFactory()
    }

    fun createLineFactory(): LineFactory {
        return LineFactory(latitudeSpanCalculator, longitudeSpanCalculator)
    }

    fun createRectangleFactory(): RectangleFactory {
        return RectangleFactory(latitudeSpanCalculator, longitudeSpanCalculator)
    }
}
