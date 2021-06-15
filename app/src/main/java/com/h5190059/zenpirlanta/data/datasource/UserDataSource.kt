package com.h5190059.zenpirlanta.data.datasource

import com.h5190059.zenpirlanta.data.UserResponseItem
import com.h5190059.zenpirlanta.util.Resource
import kotlinx.coroutines.flow.Flow

interface UserDataSource {

    fun getAllUsers(): Flow<Resource<List<UserResponseItem>>>
}