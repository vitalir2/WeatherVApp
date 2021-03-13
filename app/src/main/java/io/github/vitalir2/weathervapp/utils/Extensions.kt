package io.github.vitalir2.weathervapp.utils

fun Double.toCoordinateLong(): Long {
    return (this * COORDINATE_CONVERTER_VALUE).toLong()
}

fun Long.toCoordinateDouble(): Double {
    return this.toDouble() / COORDINATE_CONVERTER_VALUE
}

private const val COORDINATE_CONVERTER_VALUE = 10_000_000