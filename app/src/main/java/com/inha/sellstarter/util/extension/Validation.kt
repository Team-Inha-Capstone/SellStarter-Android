package com.inha.sellstarter.util.extension

fun validateShopName(input: String): Boolean {
    val regex = "^[가-힣a-zA-Z0-9 ]+$".toRegex()
    return input.isNotBlank() && regex.matches(input)
}
