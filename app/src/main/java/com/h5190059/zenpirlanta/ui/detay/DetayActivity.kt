package com.h5190059.zenpirlanta.ui.detay

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.h5190059.zenpirlanta.R
import com.h5190059.zenpirlanta.data.Urunler
import com.h5190059.zenpirlanta.databinding.ActivityDetayBinding
import com.h5190059.zenpirlanta.resimCek
import com.h5190059.zenpirlanta.util.Constants
import com.h5190059.zenpirlanta.util.ObjectUtil


class DetayActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetayBinding
    private var buttonBackground:Int=1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    fun init() {
        binding = ActivityDetayBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val tasinanUrunString = intent.getStringExtra(Constants.TASINAN_URUN_ADI)
        val urunModel:Urunler = ObjectUtil.jsonStringToObject(tasinanUrunString.toString())

        binding.apply {
            txtUrunDetayAd.text=urunModel.urunAdi
            imgUrunDetay.resimCek(urunModel.urunResim)
            txtUrunDetayFiyat.text=urunModel.urunFiyat
            txtUrunAgirlik.text=urunModel.urunDetay.agirlik
            txtUrunAyar.text=urunModel.urunDetay.ayar
            txtUrunAltinTuru.text=urunModel.urunDetay.altinTuru
            txtUrunDetayTasRenk.text=urunModel.urunDetay.renk
            txtUrunDetayTasAdet.text=urunModel.urunDetay.tasAdet
            txtUrunDetayTasBuyuklugu.text=urunModel.urunDetay.tasBuyuklugu

            txtUrunDetayTasBerraklik.text=urunModel.urunDetay.berraklik
            txtUrunDetayTasSekil.text=urunModel.urunDetay.sekil
            txtUrunAciklamaBaslik.text=urunModel.urunDetay.aciklamaBaslik
            txtUrunAciklama.text=urunModel.urunDetay.aciklama
            txtUrunKodu.text=urunModel.urunDetay.kod


            btnFavori.setOnClickListener {
                favoriBgDegistir(buttonBackground)
            }


        }
    }

    private fun favoriBgDegistir(buttonBackground:Int) {
        if(buttonBackground==Constants.BTN_FAVORI_BG1){
            this.buttonBackground =Constants.BTN_FAVORI_BG2
            binding.btnFavori.setBackgroundResource(R.drawable.btn_favori_red_design)

        }
        else{
            binding.btnFavori.setBackgroundResource(R.drawable.btn_favori_design)
            this.buttonBackground=Constants.BTN_FAVORI_BG1
        }

    }
}

