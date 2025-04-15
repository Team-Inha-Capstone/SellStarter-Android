package com.inha.sellstarter_android.presentation.model

import androidx.compose.runtime.MutableState

data class ChipState(
    val text: String,
    val isSelected: MutableState<Boolean>,
)
