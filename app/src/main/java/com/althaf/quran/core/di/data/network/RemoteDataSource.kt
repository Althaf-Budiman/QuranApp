package com.althaf.quran.core.di.data.network

import android.util.Log
import com.althaf.quran.core.di.data.network.adzan.AdzanApiService
import com.althaf.quran.core.di.data.network.adzan.CityItem
import com.althaf.quran.core.di.data.network.adzan.JadwalItem
import com.althaf.quran.core.di.data.network.quran.QuranApiService
import com.althaf.quran.core.di.data.network.quran.QuranEditionItem
import com.althaf.quran.core.di.data.network.quran.SurahItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(
    private val apiService: QuranApiService,
    private val adzanApiService: AdzanApiService
) {

    suspend fun getListSurah(): Flow<NetworkResponse<List<SurahItem>>> =
        flow {
            try {
                val surahResponse = apiService.getListSurah() // SurahResponse2
                val listSurah = surahResponse.listSurah
                emit(NetworkResponse.Success(listSurah))
            } catch (e: Exception) {
                emit(NetworkResponse.Error(e.toString()))
                Log.e(RemoteDataSource::class.java.simpleName, "error: " + e.localizedMessage)
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getDetailSurahWithQuranEditions(number: Int): Flow<NetworkResponse<List<QuranEditionItem>>> =
        flow {
            try {
                val ayahResponse = apiService.getDetailSurahWithQuranEditions(number)
                val quranEditions = ayahResponse.quranEdition
                emit(NetworkResponse.Success(quranEditions))
            } catch (e: Exception) {
                emit(NetworkResponse.Error(e.toString()))
                Log.e(RemoteDataSource::class.java.simpleName, "error: " + e.localizedMessage)
            }
        }.flowOn(Dispatchers.IO)

    suspend fun searchCity(city: String): Flow<NetworkResponse<List<CityItem>>> =
        flow {
            try {
                val cityResponse = adzanApiService.searchCit(city)
                val cities = cityResponse.listCity
                emit(NetworkResponse.Success(cities))
            } catch (e: Exception) {
                emit(NetworkResponse.Error(e.toString()))
                Log.e(RemoteDataSource::class.java.simpleName, "error: " + e.localizedMessage)
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getDailyAdzanTime(
        id: String,
        year: String,
        month: String,
        date: String
    ): Flow<NetworkResponse<JadwalItem>> = flow<NetworkResponse<JadwalItem>> {
        try {
            val dailyResponse = adzanApiService.getDailyAdzanTime(id, year, month, date)
            val jadwalResponse = dailyResponse.data.jadwalItem
            emit(NetworkResponse.Success(jadwalResponse))
        } catch (e: Exception) {
            emit(NetworkResponse.Error(e.toString()))
            Log.e(RemoteDataSource::class.java.simpleName, "error: " + e.localizedMessage)
        }
    }.flowOn(Dispatchers.IO)
}
