package com.h5190059.zenpirlanta.ui.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.h5190059.zenpirlanta.data.UserResponseItem
import com.h5190059.zenpirlanta.data.repository.UserRepository
import com.h5190059.zenpirlanta.util.ResourceStatus
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    private  val userRepository: UserRepository = UserRepository()

    init {
        getAllUsers()
    }

    var allUsersLiveData = MutableLiveData<List<UserResponseItem>>()
    var loading   : MutableLiveData<Boolean>? = MutableLiveData()
    var error =    MutableLiveData<Throwable>()

    fun getAllUsers()  = viewModelScope.launch {

        userRepository.getAllUsers()

            .asLiveData(viewModelScope.coroutineContext).observeForever {

                when(it.status) {
                    ResourceStatus.LOADING -> {
                        loading?.postValue(true)
                    }

                    ResourceStatus.SUCCESS -> {
                        allUsersLiveData.postValue(it.data!!)
                        loading?.postValue(false)
                    }

                    ResourceStatus.ERROR -> {
                        error.postValue(it.throwable!!)
                        loading?.postValue(false)
                    }
                }
            }
    }
}

