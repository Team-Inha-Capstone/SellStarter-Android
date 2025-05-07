package com.inha.sellstarter_android.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.floatPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object FontScaleDataStore {
    private val Context.fontScaleDataStore by preferencesDataStore(name = "font_settings")
    private val FONT_SCALE_KEY = floatPreferencesKey("font_scale")

    suspend fun saveFontScale(context: Context, scale: Float) {
        context.fontScaleDataStore.edit { prefs ->
            prefs[FONT_SCALE_KEY] = scale
        }
    }

    fun getFontScale(context: Context): Flow<Float> =
        context.fontScaleDataStore.data.map { prefs ->
            prefs[FONT_SCALE_KEY] ?: 1.0f
        }
}