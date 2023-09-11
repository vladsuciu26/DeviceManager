package com.example.summership2023cluj.common.utils

import java.text.SimpleDateFormat
import java.util.Locale

class DateHelper {
    companion object {
        const val FULL_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS"
        const val YEAR_MONTH_DATE_FORMAT = "yyyy MM dd"

        fun convertFromDateToDate(date: String?, originalFormat: String, targetFormat: String): String {
            val fromFormat = SimpleDateFormat(originalFormat, Locale.getDefault())
            val toFormat = SimpleDateFormat(targetFormat, Locale.getDefault())

            date?.let {
                return toFormat.format(fromFormat.parse(it) ?: "")
            }

            return ""
        }
    }
}