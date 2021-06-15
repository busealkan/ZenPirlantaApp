package com.h5190059.zenpirlanta.data.datasource.remote

import com.h5190059.zenpirlanta.data.KategoriResponseItem
import com.h5190059.zenpirlanta.data.Urunler
import com.h5190059.zenpirlanta.data.UserResponseItem
import com.h5190059.zenpirlanta.util.Constants
import com.h5190059.zenpirlanta.util.Constants.JSON_USER_DOSYA_ADI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ZenPirlantaService {

    @GET(JSON_USER_DOSYA_ADI)
    suspend fun getAllUsers(): Response<List<UserResponseItem>>

    @GET(Constants.JSON_KATEGORI_VE_URUNLER_DOSYA_ADI)
    suspend fun kategorileriGetir(): Response<List<KategoriResponseItem>>

    @GET(Constants.JSON_KATEGORI_VE_URUNLER_DOSYA_ADI)
    suspend fun urunleriGetir(): Response<List<Urunler>>


    companion object  {

        fun build(): ZenPirlantaService {

            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val okHtppClient =  OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constants.BASE_URL)
                .client(okHtppClient)
                .build()

            return retrofit.create(ZenPirlantaService::class.java)
        }
    }

}