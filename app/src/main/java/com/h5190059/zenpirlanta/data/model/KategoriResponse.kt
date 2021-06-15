package com.h5190059.zenpirlanta.data

import com.google.gson.annotations.SerializedName

data class KategoriResponseItem(
    @SerializedName("kategoriAdi")
    val kategoriAdi: String,
    @SerializedName("kategoriResimUrl")
    val kategoriResimUrl: String,
    @SerializedName("urunler")
    val urunler: List<Urunler>
)

data class Urunler(
    @SerializedName("urunAdi")
    val urunAdi: String,
    @SerializedName("urunDetay")
    val urunDetay: UrunDetay,
    @SerializedName("urunFiyat")
    val urunFiyat: String,
    @SerializedName("urunResim")
    val urunResim: String
)

data class UrunDetay(
    @SerializedName("aciklama")
    val aciklama: String,
    @SerializedName("aciklamaBaslik")
    val aciklamaBaslik: String,
    @SerializedName("agirlik")
    val agirlik: String,
    @SerializedName("altinTuru")
    val altinTuru: String,
    @SerializedName("ayar")
    val ayar: String,
    @SerializedName("berraklik")
    val berraklik: String,
    @SerializedName("kod")
    val kod: String,
    @SerializedName("renk")
    val renk: String,
    @SerializedName("sekil")
    val sekil: String,
    @SerializedName("tasAdet")
    val tasAdet: String,
    @SerializedName("tasBuyuklugu")
    val tasBuyuklugu: String
)