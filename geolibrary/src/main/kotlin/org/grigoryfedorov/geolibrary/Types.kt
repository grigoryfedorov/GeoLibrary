package org.grigoryfedorov.geolibrary

/**
 * type aliases are used to make type changes easier if needed.
 * For example Float can be used, because it probably enought to represent Earth's coordinates
 * See also https://stackoverflow.com/questions/385132/proper-best-type-for-storing-latitude-and-longitude?lq=1
 */

/**
 * Coordinates angle, degrees. Used for latitude and longitude
 */
typealias Angle = Double

/**
 * Earth Radius, meters. Point's elevation lies or extends Earth radius
 */
typealias Radius = Double

/**
 * Distance between points, meters
 */
typealias Distance = Double

