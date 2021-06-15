package com.h5190059.zenpirlanta.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.h5190059.zenpirlanta.data.KategoriResponseItem
import com.h5190059.zenpirlanta.data.KategoriRepository

import com.h5190059.zenpirlanta.util.ResourceStatus
import kotlinx.coroutines.launch

class KategoriViewModel : ViewModel() {

    private  val kategoriRepository: KategoriRepository =KategoriRepository()

    init {
        kategorileriGetir()
    }

    var loading   : MutableLiveData<Boolean>? = MutableLiveData()
    var kategorilerLiveData = MutableLiveData<List<KategoriResponseItem>>()
    var error =    MutableLiveData<Throwable>()


    fun kategorileriGetir()  = viewModelScope.launch {

        kategoriRepository.kategorileriGetir()

            .asLiveData(viewModelScope.coroutineContext).observeForever {

                when(it.status) {
                    ResourceStatus.LOADING -> {
                        loading?.postValue(true)
                    }

                    ResourceStatus.SUCCESS -> {
                        kategorilerLiveData.postValue(it.data!!)
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

