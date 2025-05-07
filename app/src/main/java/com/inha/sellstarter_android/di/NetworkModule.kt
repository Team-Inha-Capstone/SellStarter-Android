package com.inha.sellstarter_android.di

import com.google.zxing.client.android.BuildConfig
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun provideRetrofit(
        client: OkHttpClient,
        jsonConverter: Converter.Factory,
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl("")
            .client(client)
            .addConverterFactory(jsonConverter)
            .build()

    @Provides
    @Singleton
    fun provideJsonConverterFactory(): Converter.Factory {
        val json =
            Json {
                ignoreUnknownKeys = true // JSON에 정의되지 않은 필드 무시
                isLenient = true // 유연한 파싱 허용'
                explicitNulls = false
                prettyPrint = true
            }
        return json.asConverterFactory("application/json".toMediaType())
    }

    @Provides
    @Singleton
    fun provideHttpClient(
        @LoggingInterceptorQualifier loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }


    @Provides
    @Singleton
    @LoggingInterceptorQualifier
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
        }
    }

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class AuthInterceptorQualifier

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class LoggingInterceptorQualifier
}
