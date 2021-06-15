package com.h5190059.zenpirlanta.data

import com.google.gson.annotations.SerializedName


data class UserResponseItem(
    @SerializedName("name")
    val name: String?,
    @SerializedName("surname")
    val surname: String?,
    @SerializedName("ePosta")
    val ePosta: String?,
    @SerializedName("password")
    val password: String?,
    @SerializedName("resimUrl")
    val resimUrl: String?



)
