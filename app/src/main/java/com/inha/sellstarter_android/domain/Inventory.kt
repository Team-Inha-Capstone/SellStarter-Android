package com.inha.sellstarter_android.domain

data class Inventory(
    val id: Int,
    val name: String,
    val quantity: Int,
    val image : String,
    val isSoldOut : Boolean,
    val updatedAt : String
)