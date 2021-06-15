package com.h5190059.zenpirlanta.data.dataSource

import com.h5190059.zenpirlanta.data.Urunler
import com.h5190059.zenpirlanta.util.Resource
import kotlinx.coroutines.flow.Flow

interface UrunDataSource {
    fun urunleriGetir(): Flow<Resource<List<Urunler>>>
}