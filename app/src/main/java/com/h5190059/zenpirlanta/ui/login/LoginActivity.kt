package com.h5190059.zenpirlanta.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.h5190059.zenpirlanta.R
import com.h5190059.zenpirlanta.data.UserResponseItem
import com.h5190059.zenpirlanta.databinding.ActivityLoginBinding
import com.h5190059.zenpirlanta.ui.kategori.KategoriActivity
import com.h5190059.zenpirlanta.util.Constants

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private var userViewModel: UserViewModel? = null
    private var userList:List<UserResponseItem>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()

    }

    fun init() {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            btnGirisYap.setOnClickListener {
                bosKontrol()
            }
        }
    }

    fun bosKontrol() {
        var ePosta: kotlin.String? = binding.txtEPosta.text.toString().trim()
        var sifre: kotlin.String? = binding.txtSifre.text.toString().trim()
        if (ePosta.equals(resources.getString(R.string.bosKontrol)) || sifre.equals(resources.getString(R.string.bosKontrol)) ) {
            Toast.makeText(applicationContext, resources.getString(R.string.toastBosAlan), Toast.LENGTH_LONG).show()
        } else {
            if(ePostaFormatiGecerliMi(ePosta)==true){
                initViewModel(ePosta, sifre)
            } else {
                Toast.makeText(applicationContext, resources.getString(R.string.toastEPostaFormat), Toast.LENGTH_LONG).show();
            }

        }
    }

    fun ePostaFormatiGecerliMi(ePosta: CharSequence?): Boolean {
        // e-posta formatı kontrol
        if (TextUtils.isEmpty(ePosta)) {
            return true
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(ePosta).matches()
        }
    }



    fun initViewModel(ePosta: String?, sifre: String?)
    {
        userViewModel= UserViewModel()
        userViewModel?.apply {
            allUsersLiveData?.observe(this@LoginActivity, Observer {
                it.run {
                    kullaniciKontrolEt(it,ePosta,sifre)
                }
            })

            error?.observe(this@LoginActivity, Observer {

                it.run {
                }
            })

            loading?.observe(this@LoginActivity, Observer {

                //Handle loading
            })
        }
    }

    private fun kullaniciKontrolEt(it: List<UserResponseItem>?, ePosta: String?, sifre: String?) {
        userList=it
        var kullaniciAdet:Int=1
        for (userModel:UserResponseItem in userList!!){
            if(userModel.ePosta.toString().equals(ePosta) && userModel.password.toString().equals(sifre)){
                var kullaniciAd:String=userModel.name!!
                var kullaniciSoyad:String=userModel.surname!!
                var kullaniciResim:String=userModel.resimUrl!!
                kategoriActivity(kullaniciAd,kullaniciSoyad,kullaniciResim)
                break
            }
            else{
                kullaniciAdet++
                if(kullaniciAdet== userList!!.size){
                    Toast.makeText(this@LoginActivity, resources.getString(R.string.toastGirisBasarisiz), Toast.LENGTH_LONG).show()
                    break
                }
            }
        }
    }


    private fun kategoriActivity(kullaniciAd: String, kullaniciSoyad: String, resimUrl: String) {
        val kategoriIntent = Intent(this@LoginActivity, KategoriActivity::class.java)
        kategoriIntent.putExtra(Constants.TASINAN_KULLANICI_AD,kullaniciAd)
        kategoriIntent.putExtra(Constants.TASINAN_KULLANICI_SOYAD,kullaniciSoyad)
        kategoriIntent.putExtra(Constants.TASINAN_KULLANICI_RESIM_URL,resimUrl)
        startActivity(kategoriIntent)
        finish()
    }
}