package com.inha.sellstarter.data.mapper

import com.inha.sellstarter.data.model.response.home.WeeklySalesResponseDto
import com.inha.sellstarter.data.model.response.home.YearlySalesResponseDto
import com.inha.sellstarter.domain.model.WeeklySales
import com.inha.sellstarter.domain.model.YearlySales
import java.util.Calendar

fun WeeklySalesResponseDto.toDomain(): WeeklySales {
    return WeeklySales(
        mon = this.mon,
        tue = this.tue,
        wed = this.wed,
        thu = this.thu,
        fri = this.fri,
        sat = this.sat,
        sun = this.sun,
        weeklySum = this.sum(),
    )
}

fun YearlySalesResponseDto.toDomain(): YearlySales {
    return YearlySales(
        jan = this.jan,
        feb = this.feb,
        mar = this.mar,
        apr = this.apr,
        may = this.may,
        jun = this.jun,
        jul = this.jul,
        aug = this.aug,
        sep = this.sep,
        oct = this.oct,
        nov = this.nov,
        dec = this.dec,
        currentMonthSum = this.currentMonthSum(),
    )
}

fun WeeklySalesResponseDto.sum(): Int {
    return mon + tue + wed + thu + fri + sat + sun
}

fun YearlySalesResponseDto.currentMonthSum(): Int {
    val calendar = Calendar.getInstance()
    val currentMonth = calendar.get(Calendar.MONTH) + 1
    return when (currentMonth) {
        1 -> jan
        2 -> feb
        3 -> mar
        4 -> apr
        5 -> may
        6 -> jun
        7 -> jul
        8 -> aug
        9 -> sep
        10 -> oct
        11 -> nov
        12 -> dec
        else -> 0
    }
}
