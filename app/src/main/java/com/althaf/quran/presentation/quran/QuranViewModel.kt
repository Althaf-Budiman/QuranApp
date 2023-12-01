package com.althaf.quran.presentation.quran

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.althaf.quran.core.di.data.QuranRepository
import com.althaf.quran.core.di.data.Resource
import com.althaf.quran.core.di.domain.model.QuranEdition
import com.althaf.quran.core.di.domain.model.Surah

class QuranViewModel(private val quranRepository: QuranRepository) : ViewModel() {
    fun getListSurah(): LiveData<Resource<List<Surah>>> =
        quranRepository.getListSurah().asLiveData()

    fun getDetailSurahWithQuranEditions(number: Int): LiveData<Resource<List<QuranEdition>>> =
        quranRepository.getDetailSurahWithQuranEditions(number).asLiveData()
}