package org.grigoryfedorov.geolibrary

/**
 * The Earth's equatorial radius a, or semi-major axis, is the distance from its center to the equator and equals
 * https://en.wikipedia.org/wiki/Earth_radius#Equatorial_radius
 */
const val RADIUS_EQUATORIAL_A_METERS: Radius = 6378137.0

/**
 * The Earth's polar radius b, or semi-minor axis,
 * is the distance from its center to the North and South Poles, and equals
 * https://en.wikipedia.org/wiki/Earth_radius#Polar_radius
 */
const val RADIUS_POLAR_B_METERS: Radius = 6356752.3142

private const val ANGLE_360_DEGREES: Angle = 360.0
private const val ANGLE_180_DEGREES: Angle = 180.0
private const val ANGLE_90_DEGREES: Angle = 90.0

const val RIGHT_ANGLE_DEGREES = ANGLE_90_DEGREES

const val LATITUDE_NORTH_BOUND = ANGLE_90_DEGREES
const val LATITUDE_SOUTH_BOUND = -ANGLE_90_DEGREES
const val LATITUDE_FULL_RANGE = ANGLE_180_DEGREES
const val LATITUDE_ROUND_CIRCLE = ANGLE_360_DEGREES

const val LONGITUDE_EAST_BOUND = ANGLE_180_DEGREES
const val LONGITUDE_WEST_BOUND = -ANGLE_180_DEGREES
const val LONGITUDE_FULL_RANGE = ANGLE_360_DEGREES
const val LONGITUDE_FLIP_RANGE = ANGLE_180_DEGREES

private const val MAX_SPAN = ANGLE_180_DEGREES
const val LINE_MAX_SPAN = MAX_SPAN
const val RECTANGLE_MAX_SPAN = MAX_SPAN

const val DEGREE_TO_RADIANS_COEFFICIENT: Angle = Math.PI / ANGLE_180_DEGREES

const val LATITUDE_DEGREE_METERS = 111300
