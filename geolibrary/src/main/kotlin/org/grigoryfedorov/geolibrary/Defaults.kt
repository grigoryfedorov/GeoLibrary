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

val DEFAULT_DISTANCE_CALCULATOR: DistanceCalculator = simpleStraitLineCalculator
val DEFAULT_POINT_ON_LINE_FINDER: PointOnLineFinder = simpleStraitLineCalculator
val DEFAULT_POINT_TRANSLATOR: PointTranslator = PointTranslator(latitudeNormalizer, longitudeNormalizer)
val DEFAULT_CONTAINED_IN_RECTANGLE_CALCULATOR: ContainedInRectangleCalculator = containedInRectangleCalculator
val DEFAULT_LONGITUDE_NORMALIZER = longitudeNormalizer
val DEFAULT_LATITUDE_SPAN_CALCULATOR = latitudeSpanCalculator
val DEFAULT_LONGITUDE_SPAN_CALCULATOR = longitudeSpanCalculator
