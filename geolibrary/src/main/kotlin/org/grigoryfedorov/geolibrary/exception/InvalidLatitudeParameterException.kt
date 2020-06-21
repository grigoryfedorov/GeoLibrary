package org.grigoryfedorov.geolibrary.exception

import java.security.InvalidParameterException

/**
 * Latitude value is incorrect, most likely outside value range
 */
class InvalidLatitudeParameterException(msg: String)
    : InvalidParameterException(msg)
