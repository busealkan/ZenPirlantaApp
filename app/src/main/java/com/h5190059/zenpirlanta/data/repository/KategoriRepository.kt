package com.h5190059.zenpirlanta.data

import com.h5190059.zenpirlanta.data.KategoriResponseItem
import com.h5190059.zenpirlanta.data.dataSource.remote.RemoteKategoriDataSource
import com.h5190059.zenpirlanta.util.Resource
import kotlinx.coroutines.flow.Flow

class KategoriRepository {
    private var kategoriDataSource: KategoriDataSource?=null

    init {
        kategoriDataSource = RemoteKategoriDataSource()
    }

    fun kategorileriGetir(): Flow<Resource<List<KategoriResponseItem>>>
    {
        return kategoriDataSource!!.kategorileriGetir()
    }

}