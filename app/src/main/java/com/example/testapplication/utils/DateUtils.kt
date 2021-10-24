package com.example.testapplication.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.ParseException
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.*

object DateUtils {
    @RequiresApi(Build.VERSION_CODES.O)
    fun getDateObjectFromString(date: String?): LocalDate {
        val format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZZZZZ")
        try {
            return LocalDate.parse(date, format)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return LocalDate.now()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getDiffDays(dateReference: LocalDate): Long {
        val today = LocalDate.now()
        val diff: Long = ChronoUnit.DAYS.between(today, dateReference)
        return diff
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getStringDateFromDate(date: String?): String {
        val format = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZZZZZ")
        try {
            return LocalDate.parse(date, format).format(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault()))
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.getDefault()))
    }
}