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

/**
 * On latitude degrees in meters. Used in distance calculations
 */
const val LATITUDE_DEGREE_METERS = 111300

/**
 * 360 degrees constant
 */
private const val ANGLE_360_DEGREES: Angle = 360.0

/**
 * 180 degrees constant
 */
private const val ANGLE_180_DEGREES: Angle = 180.0

/**
 * 90 degrees constant
 */
private const val ANGLE_90_DEGREES: Angle = 90.0

/**
 * right angle in degrees constant
 */
const val RIGHT_ANGLE_DEGREES = ANGLE_90_DEGREES

/**
 * North latitude range bound in degrees
 */
const val LATITUDE_NORTH_BOUND = ANGLE_90_DEGREES

/**
 * South latitude range bound in degrees
 */
const val LATITUDE_SOUTH_BOUND = -ANGLE_90_DEGREES

/**
 * Full latitude range, from North to South bound in degrees
 */
const val LATITUDE_FULL_RANGE = ANGLE_180_DEGREES

/**
 * Latitude range around whole Earth - can be added or subtracted without change
 */
const val LATITUDE_ROUND_CIRCLE = ANGLE_360_DEGREES

/**
 * East longitude range bound in degrees
 */
const val LONGITUDE_EAST_BOUND = ANGLE_180_DEGREES

/**
 * West longitude range bound in degrees
 */
const val LONGITUDE_WEST_BOUND = -ANGLE_180_DEGREES

/**
 * Full longitude range, from West to East bound in degrees
 */
const val LONGITUDE_FULL_RANGE = ANGLE_360_DEGREES

/**
 * Amount of degrees to flip longitude to another hemisphere
 */
const val LONGITUDE_FLIP_RANGE = ANGLE_180_DEGREES

private const val MAX_SPAN = ANGLE_180_DEGREES

/**
 * Max latitude and longitude delta for Line allowed
 */
const val LINE_MAX_SPAN = MAX_SPAN

/**
 * Max latitude and longitude delta for Rectangle allowed
 */
const val RECTANGLE_MAX_SPAN = MAX_SPAN

/**
 * Coefficient to convert degree to radian
 */
const val DEGREE_TO_RADIANS_COEFFICIENT: Angle = Math.PI / ANGLE_180_DEGREES

/**
 * Minimum Point count allowed to create PolyLine
 */
const val POLY_LINE_MIN_POINTS_COUNT = 2
