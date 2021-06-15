package com.h5190059.zenpirlanta.data.repository

import com.h5190059.zenpirlanta.data.KategoriDataSource
import com.h5190059.zenpirlanta.data.KategoriResponseItem
import com.h5190059.zenpirlanta.data.Urunler
import com.h5190059.zenpirlanta.data.dataSource.UrunDataSource
import com.h5190059.zenpirlanta.data.dataSource.remote.RemoteKategoriDataSource
import com.h5190059.zenpirlanta.data.dataSource.remote.RemoteUrunDataSource
import com.h5190059.zenpirlanta.util.Resource
import kotlinx.coroutines.flow.Flow

class UrunRepository {
    private var urunDataSource: UrunDataSource?=null

    init {
        urunDataSource = RemoteUrunDataSource()
    }

    fun urunleriGetir(): Flow<Resource<List<Urunler>>>
    {
        return urunDataSource!!.urunleriGetir()
    }
}
