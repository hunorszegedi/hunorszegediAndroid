package main

import java.time.LocalDate

data class Date(
    val year: Int = LocalDate.now().year,
    val month: Int = LocalDate.now().monthValue,
    val day: Int = LocalDate.now().dayOfMonth
): Comparable<Date> {
    override fun compareTo(other: Date): Int {

        val yearComparison = year.compareTo(other.year)
        if (yearComparison != 0) {
            return yearComparison
        }

        val monthComparison = month.compareTo(other.month)
        if (monthComparison != 0) {
            return monthComparison
        }

        // ha az ev es honap ugyanaz, nezem a napokat
        return day.compareTo(other.day)
    }
}

class DateComparator : Comparator<Date> {
    override fun compare(date1: Date, date2: Date): Int {
        if (date1.year != date2.year) {
            return date1.year.compareTo(date2.year)
        }
        if (date1.month != date2.month) {
            return date1.month.compareTo(date2.month)
        }
        return date1.day.compareTo(date2.day)
    }
}

fun Date.isLeapYear(): Boolean {
    // 4-el oszthato, de nem oszthato 100-al (kiveve 400)
    return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)
}

fun Date.isValidDate(): Boolean {
    try {
        if (year < 0 || year > LocalDate.now().year) {
            return false
        }

        if (month < 1 || month > 12) {
            return false
        }

        if (isLeapYear() && (month == 2)){
            if(day > 29)
                return false
        }

        if(!isLeapYear() && month == 2){
            if(day > 28)
                return false
        }

        LocalDate.of(year, month, day)
        return true // ha nem dob exception -t, akk valid.
    } catch (e: Exception) {
        return false
    }
}