package org.grigoryfedorov.geolibrary.distance

import org.grigoryfedorov.geolibrary.Angle
import org.grigoryfedorov.geolibrary.RADIUS_EQUATORIAL_A_METERS
import org.grigoryfedorov.geolibrary.DEGREE_TO_RADIANS_COEFFICIENT
import org.grigoryfedorov.geolibrary.RADIUS_POLAR_B_METERS
import org.grigoryfedorov.geolibrary.Radius
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt


/**
 * Calculates Earth WGS84 radius for specific latitude
 *
 * see https://planetcalc.com/7721/
 * https://en.wikipedia.org/wiki/Earth_radius#Geocentric_radius
 */
class WGS84RadiusCalculator : RadiusCalculator {

    private val a2 = RADIUS_EQUATORIAL_A_METERS.pow(2)
    private val b2 = RADIUS_POLAR_B_METERS.pow(2)

    override fun getRadius(latitude: Angle): Radius {
        val radians = latitude * DEGREE_TO_RADIANS_COEFFICIENT
        val a2cos2 = a2 * cos(radians).pow(2)
        val b2sin2 = b2 * sin(radians).pow(2)

        return sqrt((a2 * a2cos2 + b2 * b2sin2) / (a2cos2 + b2sin2))
    }
}
