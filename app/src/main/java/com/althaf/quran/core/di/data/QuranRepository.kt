package com.althaf.quran.core.di.data

import com.althaf.quran.core.di.data.network.NetworkBoundResource
import com.althaf.quran.core.di.data.network.NetworkResponse
import com.althaf.quran.core.di.data.network.RemoteDataSource
import com.althaf.quran.core.di.domain.model.QuranEdition
import com.althaf.quran.core.di.domain.model.Surah
import com.althaf.quran.core.di.domain.repository.IQuranRepository
import com.althaf.quran.core.di.data.network.quran.QuranEditionItem
import com.althaf.quran.core.di.data.network.quran.SurahItem
import com.althaf.quran.utils.DataMapper
import kotlinx.coroutines.flow.Flow

class QuranRepository(private val remoteDataSource: RemoteDataSource): IQuranRepository {
    override fun getListSurah(): Flow<Resource<List<Surah>>> {
        return object : NetworkBoundResource<List<Surah>, List<SurahItem>>() {

            override fun fetchFromNetwork(data: List<SurahItem>): Flow<List<Surah>> {
                return DataMapper.mapResponseToDomain(data)
            }

            override suspend fun createCall(): Flow<NetworkResponse<List<SurahItem>>> {
                return remoteDataSource.getListSurah()
            }

        }.asFlow()
    }

    override fun getDetailSurahWithQuranEditions(number: Int): Flow<Resource<List<QuranEdition>>> {
        return object : NetworkBoundResource<List<QuranEdition>, List<QuranEditionItem>>() {
            override fun fetchFromNetwork(data: List<QuranEditionItem>): Flow<List<QuranEdition>> {
                return DataMapper.mapResponseToDomain(data)
            }

            override suspend fun createCall(): Flow<NetworkResponse<List<QuranEditionItem>>> {
                return remoteDataSource.getDetailSurahWithQuranEditions(number)
            }

        }.asFlow()
    }
}