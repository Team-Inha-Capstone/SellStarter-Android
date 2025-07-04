package com.inha.sellstarter.data.model.response.home

import kotlinx.serialization.Serializable

@Serializable
data class WeeklySalesResponseDto(
    val mon: Int,
    val tue: Int,
    val wed: Int,
    val thu: Int,
    val fri: Int,
    val sat: Int,
    val sun: Int,
)

@Serializable
data class YearlySalesResponseDto(
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
)
