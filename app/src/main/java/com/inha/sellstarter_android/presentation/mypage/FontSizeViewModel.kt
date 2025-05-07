package com.inha.sellstarter_android.presentation.mypage

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.inha.sellstarter_android.data.datasource.local.FontSizeDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FontSizeViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : ViewModel() {
    val fontScale: StateFlow<Float> = FontSizeDataStore.getFontScale(context)
        .stateIn(viewModelScope, SharingStarted.Eagerly, 1.0f)

    fun updateFontScale(scale: Float) {
        viewModelScope.launch {
            FontSizeDataStore.saveFontScale(context, scale)
        }
    }
}