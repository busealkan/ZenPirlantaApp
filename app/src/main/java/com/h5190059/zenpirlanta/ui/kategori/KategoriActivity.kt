package com.h5190059.zenpirlanta.ui.kategori

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.h5190059.zenpirlanta.ItemClickListener
import com.h5190059.zenpirlanta.R
import com.h5190059.zenpirlanta.data.KategoriResponseItem
import com.h5190059.zenpirlanta.databinding.ActivityKategoriBinding
import com.h5190059.zenpirlanta.resimCek
import com.h5190059.zenpirlanta.ui.KategoriViewModel
import com.h5190059.zenpirlanta.ui.urun.UrunActivity
import com.h5190059.zenpirlanta.util.*
import java.util.*
import com.h5190059.zenpirlanta.util.AlertUtil.AlertSecilen



class KategoriActivity : AppCompatActivity() {
    private lateinit var binding: ActivityKategoriBinding
    private var kategoriViewModel: KategoriViewModel? = null
    private lateinit var kategoriAdapter: KategoriAdapter
    private var kategoriList:List<KategoriResponseItem>?=null
    private var progressDialog:ProgressDialog?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    override fun onBackPressed() {
        cikisAlert()
    }

    fun init() {
        binding = ActivityKategoriBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val kullaniciAd:String? = intent.getStringExtra(Constants.TASINAN_KULLANICI_AD)
        val kullaniciSoyad:String? = intent.getStringExtra(Constants.TASINAN_KULLANICI_SOYAD)
        val kullaniciResimUrl:String? = intent.getStringExtra(Constants.TASINAN_KULLANICI_RESIM_URL)

        progressDialog=ProgressUtil.progressDialogOlustur(this@KategoriActivity,resources.getString(R.string.progressMessage))!!
        initViewModel()

        binding.apply {
            txtKullaniciAd.setText(kullaniciAd)
            txtKullaniciSoyad.setText(kullaniciSoyad)
            imgProfil.resimCek(kullaniciResimUrl!!)

            searchKategoriArama.setOnQueryTextListener(object :SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String): Boolean {
                    kategoriArama(newText)
                    return false
                }
            })

        }
    }

    private fun kategoriArama(kategoriAra: String?) {
        kategoriAra?.let {
            kategoriList?.let{
                var kategoriListFiltre = it.filter { it.kategoriAdi.toLowerCase(Locale(Constants.LOCALE)).contains(kategoriAra.toLowerCase(Locale(Constants.LOCALE))) }
                kategoriAdapter.setData(kategoriListFiltre)
                kategoriAdapter.notifyDataSetChanged()
            }
        }

    }

    private fun initViewModel() {
        kategoriViewModel= KategoriViewModel()

        kategoriViewModel?.apply {

            kategorilerLiveData?.observe(this@KategoriActivity, Observer {

                it.run {
                    kategoriList = it
                    initRecycleView(it)
                    ProgressUtil.progressDialogKapat(progressDialog)

                }
            })

            error?.observe(this@KategoriActivity, Observer {

                it.run {
                }
            })

            loading?.observe(this@KategoriActivity, Observer {

                //Handle loading
            })
        }
    }

    private fun initRecycleView(kategoriList: List<KategoriResponseItem>?) {
            binding.apply {
                kategoriAdapter = KategoriAdapter(kategoriList!!, object : ItemClickListener {
                    override fun onItemClick(position: Int) {
                        val tiklananKategori: KategoriResponseItem = kategoriList.get(position)
                        urunEkraniniAc(tiklananKategori)
                    }
                })
                rcvKategoriler.adapter = kategoriAdapter
                rcvKategoriler.layoutManager = StaggeredGridLayoutManager(Constants.SPAN_COUNT, StaggeredGridLayoutManager.VERTICAL)

            }
    }

    private fun urunEkraniniAc(tiklananKategori: KategoriResponseItem) {
        val urunIntent = Intent(applicationContext, UrunActivity::class.java)
        val tiklananKategoriString:String = ObjectUtil.objectToString(tiklananKategori)
        urunIntent.putExtra(Constants.TASINAN_KATEGORI_ADI, tiklananKategoriString)
        startActivity(urunIntent)
    }


    private fun cikisAlert() {
        AlertUtil.alertGoster(this@KategoriActivity,
            R.style.AlertDialogTheme,resources.getDrawable(R.drawable.exiticon), resources.getString(R.string.alertCikisTitle),resources.getString(R.string.alertCikisMessage),resources.getString(R.string.alertCikisNegativeButon),resources.getString(R.string.alertCikisPozitifButon),AlertSecilen.CIKIS
        )
    }
}
