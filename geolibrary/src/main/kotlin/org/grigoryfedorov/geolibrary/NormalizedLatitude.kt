package org.grigoryfedorov.geolibrary

/**
 * Normalized latitude together with flip longitude flag
 * True flipLongitude means that longitude needs to be flipped to another hemisphere
 *
 * @property latitude normalized latitude in correct range, in degrees
 * @see [LATITUDE_NORTH_BOUND] and [LATITUDE_SOUTH_BOUND]
 * @property flipLongitude if longitude is need to be flipped to other hemisphere as result of normalization
 */
class NormalizedLatitude(
        val latitude: Angle,
        val flipLongitude: Boolean
)
