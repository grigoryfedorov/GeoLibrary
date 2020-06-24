package org.grigoryfedorov.geolibrary

/**
 * Normalized latitude together with flip longitude flag
 * True flipLongitude means that longitude needs to be flipped to another hemisphere
 */
class NormalizedLatitude(
        val latitude: Angle,
        val flipLongitude: Boolean
)
