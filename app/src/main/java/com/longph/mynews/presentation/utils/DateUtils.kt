package com.longph.mynews.presentation.utils

import java.text.SimpleDateFormat

class DateUtils {

    companion object {
        var longDateFormat = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'")
        var shortDateFormat = SimpleDateFormat("MMM dd, yyyy")

        @JvmStatic
        fun convertLongDateToShortDate(longDate: String): String {
            try {
                return shortDateFormat.format(longDateFormat.parse(longDate))
            } catch (e: Exception) {
            }
            return ""
        }
    }
}