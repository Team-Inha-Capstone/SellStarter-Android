package com.inha.sellstarter_android.util.extension

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun String.toPrettyDate(): String {
    return try {
        val parsed = LocalDateTime.parse(this, DateTimeFormatter.ISO_DATE_TIME)
        parsed.format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm", Locale.getDefault()))
    } catch (e: Exception) {
        ""
    }
}