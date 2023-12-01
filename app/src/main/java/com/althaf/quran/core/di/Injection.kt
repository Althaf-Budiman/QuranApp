package com.althaf.quran.core.di

import android.content.Context
import com.althaf.quran.core.di.data.AdzanRepository
import com.althaf.quran.core.di.data.network.RemoteDataSource
import com.althaf.quran.core.di.data.QuranRepository
import com.althaf.quran.core.di.data.local.CalendarPreferences
import com.althaf.quran.core.di.data.local.LocationPreferences
import com.althaf.quran.core.di.data.network.ApiConfig

object Injection {
    val quranApiService = ApiConfig.quranApiConfig
    val adzanApiService = ApiConfig.adzanApiConfig
    val remoteDataSource = RemoteDataSource(quranApiService, adzanApiService)

    fun provideQuranRepository(): QuranRepository = QuranRepository(remoteDataSource)


    fun provideAdzanRepository(context: Context): AdzanRepository {
        val locationPreferences = LocationPreferences(context)
        val calendarPreferences = CalendarPreferences()

        return AdzanRepository(remoteDataSource, locationPreferences, calendarPreferences)
    }
}