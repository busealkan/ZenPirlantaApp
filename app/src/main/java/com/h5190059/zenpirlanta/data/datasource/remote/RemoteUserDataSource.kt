package com.h5190059.zenpirlanta.data.datasource.remote

import com.h5190059.zenpirlanta.data.datasource.UserDataSource
import com.h5190059.zenpirlanta.data.UserResponseItem
import com.h5190059.zenpirlanta.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RemoteUserDataSource  : UserDataSource {

    override  fun getAllUsers(): Flow<Resource<List<UserResponseItem>>> = flow {
        try {

            emit(Resource.Loading())

            val response = ZenPirlantaService.build().getAllUsers()

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