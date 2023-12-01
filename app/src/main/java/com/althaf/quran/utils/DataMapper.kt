package com.althaf.quran.utils

import com.althaf.quran.core.di.domain.model.Ayah
import com.althaf.quran.core.di.domain.model.City
import com.althaf.quran.core.di.domain.model.QuranEdition
import com.althaf.quran.core.di.domain.model.Surah
import com.althaf.quran.core.di.data.network.adzan.CityItem
import com.althaf.quran.core.di.data.network.adzan.JadwalItem
import com.althaf.quran.core.di.data.network.quran.AyahsItem
import com.althaf.quran.core.di.data.network.quran.QuranEditionItem
import com.althaf.quran.core.di.data.network.quran.SurahItem
import com.althaf.quran.core.di.domain.model.Jadwal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

object DataMapper {

    @JvmName("mapSurahItemToSurah")
    fun mapResponseToDomain(input: List<SurahItem>): Flow<List<Surah>> {
        val listSurah = ArrayList<Surah>()
        input.map {
            val surah = Surah(
                number = it.number,
                englishName = it.englishName,
                numberOfAyahs = it.numberOfAyahs,
                revelationType = it.revelationType,
                name = it.name,
                englishNameTranslation = it.englishNameTranslation,
            )
            listSurah.add(surah)
        }
        return flowOf(listSurah)
    }

    @JvmName("mapQuranEditionItemToQuranEdition")
    fun mapResponseToDomain(input: List<QuranEditionItem>): Flow<List<QuranEdition>> {
        val quranEditions = ArrayList<QuranEdition>()
        input.map {
            val edition = QuranEdition(
                number = it.number,
                englishName = it.englishName,
                numberOfAyahs = it.numberOfAyahs,
                revelationType = it.revelationType,
                name = it.name,
                englishNameTranslation = it.englishNameTranslation,
                ayahs = mapAyahsItemToDomain(it.ayahs)
            )
            quranEditions.add(edition)
        }
        return flowOf(quranEditions)
    }

    @JvmName("mapAyahsItemToAyah")
    fun mapAyahsItemToDomain(input: List<AyahsItem>): List<Ayah> {
        val listAyah = ArrayList<Ayah>()
        input.map {
            val ayah = Ayah(
                number = it.number,
                text = it.text,
                numberInSurah = it.numberInSurah,
                audio = it.audio
            )
            listAyah.add(ayah)
        }
        return listAyah
    }

    @JvmName("mapCityItemToDomain")
    fun mapResponseToDomain(input: List<CityItem>): Flow<List<City>> {
        val cities = ArrayList<City>()

        input.map {
            val city = City(
                it.id,
                it.lokasi
            )
            cities.add(city)
        }

        return flowOf(cities)
    }

    @JvmName("mapJadwalItemToDomain")
    fun mapResponseToDomain(input: JadwalItem): Flow<Jadwal> {
        val jadwal = Jadwal(
            date = input.date,
            imsak = input.imsak,
            isya = input.isya,
            subuh = input.subuh,
            dzuhur = input.dzuhur,
            ashar = input.ashar,
            dhuha = input.dhuha,
            terbit = input.terbit,
            tanggal = input.tanggal,
            maghrib = input.maghrib
        )
        return flowOf(jadwal)
    }
}