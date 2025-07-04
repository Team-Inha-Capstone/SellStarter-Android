package com.inha.sellstarter.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class WeeklySales(
    val mon: Int,
    val tue: Int,
    val wed: Int,
    val thu: Int,
    val fri: Int,
    val sat: Int,
    val sun: Int,
    val weeklySum: Int,
)

@Serializable
data class YearlySales(
    val jan: Int,
    val feb: Int,
    val mar: Int,
    val apr: Int,
    val may: Int,
    val jun: Int,
    val jul: Int,
    val aug: Int,
    val sep: Int,
    val oct: Int,
    val nov: Int,
    val dec: Int,
    val currentMonthSum: Int,
)

fun WeeklySales.toList(): List<Int> = listOf(mon, tue, wed, thu, fri, sat, sun)

fun YearlySales.toList(): List<Int> = listOf(jan, feb, mar, apr, may, jun, jul, aug, sep, oct, nov, dec)
