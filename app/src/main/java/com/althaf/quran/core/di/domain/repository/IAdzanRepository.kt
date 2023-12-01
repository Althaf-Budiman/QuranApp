package com.althaf.quran.core.di.domain.repository

import androidx.lifecycle.LiveData
import com.althaf.quran.core.di.data.Resource
import com.althaf.quran.core.di.domain.model.City
import com.althaf.quran.core.di.domain.model.Jadwal
import kotlinx.coroutines.flow.Flow

interface IAdzanRepository {

    fun getLocation(): LiveData<List<String>>
    fun searchCity(city: String): Flow<Resource<List<City>>>
    fun getDailyAdzanTime(id: String, year: String, month: String, date: String): Flow<Resource<Jadwal>>
}