package com.althaf.quran.core.di.data.network

import com.althaf.quran.core.di.data.network.adzan.AdzanApiService
import com.althaf.quran.core.di.data.network.quran.QuranApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiConfig {
    private inline fun <reified T> createApiConfig(baseUrl: String): T {
        val httpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val client = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()

        val retrofit = Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
        return retrofit.create(T::class.java)
    }

    val quranApiConfig = createApiConfig<QuranApiService>("https://api.alquran.cloud/v1/")
    val adzanApiConfig = createApiConfig<AdzanApiService>("https://api.myquran.com/v1/")
}