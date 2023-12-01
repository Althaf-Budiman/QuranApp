package com.althaf.quran.presentation.adzan

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.althaf.quran.core.di.data.AdzanRepository
import com.althaf.quran.core.di.data.Resource
import com.althaf.quran.core.di.domain.model.DailyAdzanResult

class AdzanViewModel(private val adzanRepository: AdzanRepository): ViewModel() {
    fun getDailyAdzanTime(): LiveData<Resource<DailyAdzanResult>> = adzanRepository.getDailyAdzanTimeResult()
}