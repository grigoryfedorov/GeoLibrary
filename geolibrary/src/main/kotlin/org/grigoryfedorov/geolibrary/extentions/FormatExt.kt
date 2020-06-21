package org.grigoryfedorov.geolibrary.extentions

fun Double.format(digits: Int) = "%.${digits}f".format(this)

