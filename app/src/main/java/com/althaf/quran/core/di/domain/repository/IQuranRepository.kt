package com.althaf.quran.core.di.domain.repository

import com.althaf.quran.core.di.data.Resource
import com.althaf.quran.core.di.domain.model.QuranEdition
import com.althaf.quran.core.di.domain.model.Surah
import kotlinx.coroutines.flow.Flow

interface IQuranRepository {
    fun getListSurah(): Flow<Resource<List<Surah>>>

    fun getDetailSurahWithQuranEditions(number: Int): Flow<Resource<List<QuranEdition>>>
}