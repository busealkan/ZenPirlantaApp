package com.h5190059.zenpirlanta.data.dataSource.remote

import com.h5190059.zenpirlanta.data.Urunler
import com.h5190059.zenpirlanta.data.dataSource.UrunDataSource
import com.h5190059.zenpirlanta.data.datasource.remote.ZenPirlantaService
import com.h5190059.zenpirlanta.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteUrunDataSource : UrunDataSource {

    override fun urunleriGetir(): Flow<Resource<List<Urunler>>> = flow {
        try {

            emit(Resource.Loading())

            val response = ZenPirlantaService.build().urunleriGetir()

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
