package com.althaf.quran.core.di.domain.model

import com.althaf.quran.core.di.data.Resource

data class DailyAdzanResult(
    val listAddress: List<String>,
    val adzanTime: Resource<Jadwal>,
    val currentDate: List<String>
)
