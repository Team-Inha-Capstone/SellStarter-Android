package com.inha.sellstarter.di

import com.inha.sellstarter.BuildConfig
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

const val pythonBaseUrl = ""

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    @MainRetrofit
    fun provideMainRetrofit(
        client: OkHttpClient,
        jsonConverter: Converter.Factory,
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.SPRING_API_BASE_URL)
            .client(client)
            .addConverterFactory(jsonConverter)
            .build()

    @Provides
    @Singleton
    @PythonChatbotRetrofit
    fun providePythonChatbotRetrofit(
        client: OkHttpClient,
        jsonConverter: Converter.Factory,
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl("http://$pythonBaseUrl:8000/")
            .client(client)
            .addConverterFactory(jsonConverter)
            .build()

    @Provides
    @Singleton
    @PythonDataRetrofit
    fun providePythonDataRetrofit(
        client: OkHttpClient,
        jsonConverter: Converter.Factory,
    ): Retrofit =
        Retrofit.Builder()
            .baseUrl("http://$pythonBaseUrl:8001/")
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
        @HeaderInterceptorQualifier headerInterceptor: Interceptor,
        @LoggingInterceptorQualifier loggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
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

    @Provides
    @Singleton
    @HeaderInterceptorQualifier
    fun provideHeaderInterceptor(): Interceptor {
        return Interceptor { chain ->
            val originalRequest = chain.request()
            val userId = "4"
            val requestWithHeader =
                originalRequest.newBuilder()
                    .addHeader("x-user-id", userId)
                    .build()
            chain.proceed(requestWithHeader)
        }
    }

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class LoggingInterceptorQualifier

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class HeaderInterceptorQualifier

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class MainRetrofit

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class PythonChatbotRetrofit

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class PythonDataRetrofit
}
