package com.h5190059.zenpirlanta.data.dataSource.remote

import com.h5190059.zenpirlanta.data.KategoriResponseItem
import com.h5190059.zenpirlanta.data.KategoriDataSource
import com.h5190059.zenpirlanta.data.datasource.remote.ZenPirlantaService
import com.h5190059.zenpirlanta.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteKategoriDataSource: KategoriDataSource {

    override fun kategorileriGetir(): Flow<Resource<List<KategoriResponseItem>>> = flow {
        try {

            emit(Resource.Loading())

            val response = ZenPirlantaService.build().kategorileriGetir()

            if (response.isSuccessful) {

                response.body()?.let {
                    emit(Resource.Success(it))
                }
            }

        } catch (e: Exception) {
            emit(Resource.Error(e))
            e.printStackTrace()
        }
    }
}
