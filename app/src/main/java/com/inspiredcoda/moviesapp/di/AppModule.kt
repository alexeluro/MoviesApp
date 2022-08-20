package com.inspiredcoda.moviesapp.di

import android.content.Context
import android.util.Log
import com.inspiredcoda.moviesapp.BuildConfig
import com.inspiredcoda.moviesapp.domain.remote.ApiService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.security.KeyStore
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.TrustManagerFactory
import javax.net.ssl.X509TrustManager

@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    @Singleton
    @Provides
    fun provideInterceptors(@ApplicationContext context: Context): OkHttpClient {

//        val interceptor = Interceptor { chain ->
//            if (!isInternetAvailable(context)) {
//                throw NoInternetException("Check your internet connection and try again...")
//            }
//            chain.proceed(chain.request())
//        }

//        val tokenInterceptor = Interceptor { chain ->
//            val response = chain.proceed(chain.request())
//            val errorCode = response.networkResponse?.code
//            if (errorCode == 401 || errorCode == 403) {
//                Log.d("AppModule", "Token Expired")
//                throw IllegalArgumentException("Authorization token expired!")
//            }
//            response
//        }

        val logger = HttpLoggingInterceptor().also { logger ->
            logger.level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder().also { builder ->
            builder.callTimeout(1, TimeUnit.MINUTES)
            builder.connectTimeout(1, TimeUnit.MINUTES)
            builder.readTimeout(1, TimeUnit.MINUTES)
            builder.writeTimeout(1, TimeUnit.MINUTES)
//            builder.addInterceptor(interceptor)
            builder.addInterceptor(logger)

        }
            .build()
    }

    @Singleton
    @Provides
    fun provideSerializer() = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }

    @ExperimentalSerializationApi
    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, json: Json): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .client(okHttpClient)
            .build()
    }

    @Singleton
    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

}