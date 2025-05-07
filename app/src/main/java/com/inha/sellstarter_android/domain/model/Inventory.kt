package com.inha.sellstarter_android.domain.model

data class Inventory(
    val id: Int,
    val name: String,
    val quantity: Int,
    val image : String,
    val isSoldOut : Boolean,
    val expirationDate : String,
    val updatedAt : String,
    val location : String = "2층 선반 두번째"
)