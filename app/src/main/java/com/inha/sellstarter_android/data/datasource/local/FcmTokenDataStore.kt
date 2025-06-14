package com.inha.sellstarter_android.data.datasource.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object FcmTokenDataStore {
    private val Context.fcmTokenDataStore by preferencesDataStore(name = "fcm_token_store")
    private val FCM_TOKEN_KEY = stringPreferencesKey("fcm_token")

    suspend fun saveToken(context: Context, token: String) {
        context.fcmTokenDataStore.edit { prefs ->
            prefs[FCM_TOKEN_KEY] = token
        }
    }

    fun getToken(context: Context): Flow<String?> =
        context.fcmTokenDataStore.data.map { it[FCM_TOKEN_KEY] }
}
