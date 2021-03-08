package io.github.vitalir2.weathervapp.utils

fun fromStringDayOfWeekToInt(dayOfWeek: String): Int {
    return when(dayOfWeek) {
        "MONDAY" -> 0
        "TUESDAY" -> 1
        "WEDNESDAY" -> 2
        "THURSDAY" -> 3
        "FRIDAY" -> 4
        "SATURDAY" -> 5
        "SUNDAY" -> 6
        else -> throw IllegalArgumentException()
    }
}