package com.example.pulsewave_app.screens.home_screen.historic_data

import com.patrykandpatrick.vico.core.axis.AxisPosition
import com.patrykandpatrick.vico.core.axis.formatter.AxisValueFormatter
import java.util.Calendar

val position24List = (0..23).map { it.toFloat() }
val systolic24List = List(24) { (110..129).random() }
val diastolic24List = systolic24List.map { it - (30..40).random() }
val positionWeekList = (0..6).map { it.toFloat() }
val systolicWeekList = List(7) { (100..119).random() }
val diastolicWeekList = systolicWeekList.map { it - (20..40).random() }
val positionMonthList = (0..29).map { it.toFloat() }
val systolicMonthList = List(30) { (90..119).random() }
val diastolicMonthList = systolicMonthList.map { it - (20..40).random() }
val positionYearList = (0..11).map { it.toFloat() }
val systolicYearList = List(12) { (120..130).random() }
val diastolicYearList = systolicYearList.map { it - (20..30).random() }
val positionLists =
    listOf(position24List, positionWeekList, positionMonthList, positionYearList)
val systolicLists =
    listOf(systolic24List, systolicWeekList, systolicMonthList, systolicYearList)
val diastolicLists =
    listOf(diastolic24List, diastolicWeekList, diastolicMonthList, diastolicYearList)
val historyRanges = listOf(
    "Last 24 Hours",
    "Last 7 Days",
    "Last 30 Days",
    "Last Year",
)
val currentTime = Calendar.getInstance()
val currentHour = currentTime.get(Calendar.HOUR_OF_DAY)
val pastDayFormatter = AxisValueFormatter<AxisPosition.Horizontal.Bottom> { value, _, _ ->
    val hour = (currentHour + value.toInt()) % 24
    if (hour < 12) {
        "${hour}AM"
    } else {
        "${hour - 12}PM"
    }
}
val pastWeekFormatter = AxisValueFormatter<AxisPosition.Horizontal.Bottom> { value, _, _ ->
    val day = (currentTime.get(Calendar.DAY_OF_WEEK) + value.toInt()) % 7
    when (day) {
        0 -> "Sun"
        1 -> "Mon"
        2 -> "Tue"
        3 -> "Wed"
        4 -> "Thu"
        5 -> "Fri"
        6 -> "Sat"
        else -> ""
    }
}
val pastMonthFormatter = AxisValueFormatter<AxisPosition.Horizontal.Bottom> { value, _, _ ->
    val day = (currentTime.get(Calendar.DAY_OF_MONTH) + value.toInt()) % 30
    day.toString()
}
val pastYearFormatter = AxisValueFormatter<AxisPosition.Horizontal.Bottom> { value, _, _ ->
    val month = (currentTime.get(Calendar.MONTH) + value.toInt()) % 12
    when (month) {
        0 -> "Jan"
        1 -> "Feb"
        2 -> "Mar"
        3 -> "Apr"
        4 -> "May"
        5 -> "Jun"
        6 -> "Jul"
        7 -> "Aug"
        8 -> "Sep"
        9 -> "Oct"
        10 -> "Nov"
        11 -> "Dec"
        else -> ""
    }
}
val formatters = listOf(
    pastDayFormatter,
    pastWeekFormatter,
    pastMonthFormatter,
    pastYearFormatter
)
val initialZooms = listOf(
    24f,
    7f,
    30f,
    12f
)
val xSteps = listOf(
    3f,
    1f,
    5f,
    2f
)