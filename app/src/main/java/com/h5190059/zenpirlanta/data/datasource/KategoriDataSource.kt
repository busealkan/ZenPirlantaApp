package com.h5190059.zenpirlanta.data

import com.h5190059.zenpirlanta.util.Resource
import kotlinx.coroutines.flow.Flow

interface KategoriDataSource {
    fun kategorileriGetir(): Flow<Resource<List<KategoriResponseItem>>>
}