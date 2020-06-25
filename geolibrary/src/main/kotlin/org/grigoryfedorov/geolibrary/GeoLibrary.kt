package org.grigoryfedorov.geolibrary

import org.grigoryfedorov.geolibrary.factory.LineFactory
import org.grigoryfedorov.geolibrary.factory.PointFactory
import org.grigoryfedorov.geolibrary.factory.PolyLineFactory
import org.grigoryfedorov.geolibrary.factory.RectangleFactory
import org.grigoryfedorov.geolibrary.factory.VectorFactory

/**
 * Main library class, creates factories.
 *
 * @property latitudeSpanCalculator default or custom implementation of latitude span calculator
 * @property longitudeSpanCalculator default or custom implementation of longitude span calculator
 * @property longitudeNormalizer default or custom implementation of longitude normalizer
 */
class GeoLibrary(
    private val latitudeSpanCalculator: LatitudeSpanCalculator = DEFAULT_LATITUDE_SPAN_CALCULATOR,
    private val longitudeSpanCalculator: LongitudeSpanCalculator = DEFAULT_LONGITUDE_SPAN_CALCULATOR,
    private val longitudeNormalizer: LongitudeNormalizer = DEFAULT_LONGITUDE_NORMALIZER
) {

    /**
     * Create PointFactory with LongitudeNormalizer configured through GeoLibrary constructor
     */
    fun createPointFactory(): PointFactory {
        return PointFactory(longitudeNormalizer)
    }

    /**
     * Create PolyLineFactory
     */
    fun createPolyLineFactory(): PolyLineFactory {
        return PolyLineFactory()
    }

    /**
     * Create LineFactory with LatitudeSpanCalculator and LongitudeSpanCalculator
     * configured through GeoLibrary constructor
     */
    fun createLineFactory(): LineFactory {
        return LineFactory(latitudeSpanCalculator, longitudeSpanCalculator)
    }

    /**
     * Create RectangleFactory with LatitudeSpanCalculator and LongitudeSpanCalculator
     * configured through GeoLibrary constructor
     */
    fun createRectangleFactory(): RectangleFactory {
        return RectangleFactory(latitudeSpanCalculator, longitudeSpanCalculator)
    }

    /**
     * Create VectorFactory
     */
    fun createVectorFactory(): VectorFactory {
        return VectorFactory()
    }
}
