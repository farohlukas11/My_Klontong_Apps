package com.dicoding.myklontongapps.utils

import java.text.NumberFormat
import java.util.*

object Utils {
    fun rupiahFormatter(price: Int?): String {
        val localeID = Locale("in", "ID")
        val formatter = NumberFormat.getCurrencyInstance(localeID)

        return if (price != null) {
            formatter.format(price)
        } else {
            formatter.format(0)
        }
    }
}