package com.yash.simplenotes.util

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


fun getDate(): String {
    val dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/YYYY")
    val currentTime = LocalDateTime.now()
    return dateTimeFormatter.format(currentTime)
}