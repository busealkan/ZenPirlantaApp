package com.h5190059.zenpirlanta.data.repository

import com.h5190059.zenpirlanta.data.datasource.UserDataSource
import com.h5190059.zenpirlanta.data.UserResponseItem
import com.h5190059.zenpirlanta.data.datasource.remote.RemoteUserDataSource
import com.h5190059.zenpirlanta.util.Resource
import kotlinx.coroutines.flow.Flow

class UserRepository {

    private var userDataSource: UserDataSource?=null

    init {
        userDataSource= RemoteUserDataSource()
    }

    fun getAllUsers(): Flow<Resource<List<UserResponseItem>>>
    {
        return userDataSource!!.getAllUsers()
    }

}
