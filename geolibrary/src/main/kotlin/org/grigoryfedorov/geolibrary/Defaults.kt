package org.grigoryfedorov.geolibrary

import org.grigoryfedorov.geolibrary.distance.DistanceCalculator
import org.grigoryfedorov.geolibrary.distance.PointOnLineFinder
import org.grigoryfedorov.geolibrary.distance.SimpleStraitLineCalculator


private val simpleStraitLineCalculator = SimpleStraitLineCalculator()

val DEFAULT_DISTANCE_CALCULATOR: DistanceCalculator = simpleStraitLineCalculator
val DEFAULT_POINT_ON_LINE_FINDER: PointOnLineFinder = simpleStraitLineCalculator
