package com.h5190059.zenpirlanta.ui.urun

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.h5190059.zenpirlanta.data.KategoriRepository
import com.h5190059.zenpirlanta.data.KategoriResponseItem
import com.h5190059.zenpirlanta.data.Urunler
import com.h5190059.zenpirlanta.data.repository.UrunRepository
import com.h5190059.zenpirlanta.util.ResourceStatus
import kotlinx.coroutines.launch

class UrunViewModel : ViewModel() {

    private  val urunRepository: UrunRepository = UrunRepository()

    init {
        urunleriGetir()
    }

    var loading   : MutableLiveData<Boolean>? = MutableLiveData()
    var urunlerLiveData = MutableLiveData<List<Urunler>>()
    var error =    MutableLiveData<Throwable>()


    fun urunleriGetir()  = viewModelScope.launch {

        urunRepository.urunleriGetir()

            .asLiveData(viewModelScope.coroutineContext).observeForever {

                when(it.status) {
                    ResourceStatus.LOADING -> {
                        loading?.postValue(true)
                    }

                    ResourceStatus.SUCCESS -> {
                        urunlerLiveData.postValue(it.data!!)
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

