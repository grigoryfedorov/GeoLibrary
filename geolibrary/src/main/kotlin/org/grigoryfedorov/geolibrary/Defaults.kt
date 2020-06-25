package org.grigoryfedorov.geolibrary

import org.grigoryfedorov.geolibrary.distance.DistanceCalculator
import org.grigoryfedorov.geolibrary.distance.PointOnLineFinder
import org.grigoryfedorov.geolibrary.distance.SimpleStraitLineCalculator


private val simpleStraitLineCalculator = SimpleStraitLineCalculator()
private val latitudeNormalizer = LatitudeNormalizer()
private val longitudeNormalizer = LongitudeNormalizer()
private val containedInRectangleCalculator = ContainedInRectangleCalculator()
private val latitudeSpanCalculator = LatitudeSpanCalculator()
private val longitudeSpanCalculator = LongitudeSpanCalculator()

/**
 * Default instance of DistanceCalculator
 */
val DEFAULT_DISTANCE_CALCULATOR: DistanceCalculator = simpleStraitLineCalculator

/**
 * Default instance of PointOnLineFinder
 */
val DEFAULT_POINT_ON_LINE_FINDER: PointOnLineFinder = simpleStraitLineCalculator

/**
 * Default instance of PointTranslator
 */
val DEFAULT_POINT_TRANSLATOR: PointTranslator = PointTranslator(latitudeNormalizer, longitudeNormalizer)

/**
 * Default instance of ContainedInRectangleCalculator
 */
val DEFAULT_CONTAINED_IN_RECTANGLE_CALCULATOR: ContainedInRectangleCalculator = containedInRectangleCalculator

/**
 * Default instance of LongitudeNormalizer
 */
val DEFAULT_LONGITUDE_NORMALIZER: LongitudeNormalizer = longitudeNormalizer

/**
 * Default instance of LatitudeSpanCalculator
 */
val DEFAULT_LATITUDE_SPAN_CALCULATOR: LatitudeSpanCalculator = latitudeSpanCalculator

/**
 * Default instance of LongitudeSpanCalculator
 */
val DEFAULT_LONGITUDE_SPAN_CALCULATOR: LongitudeSpanCalculator = longitudeSpanCalculator
