package com.h5190059.zenpirlanta.ui.urun

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.h5190059.zenpirlanta.ItemClickListener
import com.h5190059.zenpirlanta.R
import com.h5190059.zenpirlanta.data.KategoriResponseItem
import com.h5190059.zenpirlanta.data.Urunler
import com.h5190059.zenpirlanta.databinding.ActivityUrunBinding
import com.h5190059.zenpirlanta.ui.detay.DetayActivity
import com.h5190059.zenpirlanta.util.AlertUtil
import com.h5190059.zenpirlanta.util.Constants
import com.h5190059.zenpirlanta.util.ObjectUtil
import com.h5190059.zenpirlanta.util.ProgressUtil


class UrunActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUrunBinding
    private var urunViewModel: UrunViewModel? = null
    private lateinit var urunAdapter: UrunAdapter
    private var urunList:List<Urunler>?=null
    private var progressDialog: ProgressDialog?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    fun init() {
        binding = ActivityUrunBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tiklananKategoriUrunleri()
        progressDialog=ProgressUtil.progressDialogOlustur(this@UrunActivity,resources.getString(R.string.progressMessage))!!
        initViewModel()

        binding.apply {
            btnSirala.setOnClickListener {
                alertListele()
            }

            switchLayout.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    rcvUrunler.layoutManager = StaggeredGridLayoutManager(Constants.SPAN_COUNT, StaggeredGridLayoutManager.VERTICAL)
                }
                else {
                    rcvUrunler.setLayoutManager( LinearLayoutManager(applicationContext))
                }
            })
        }
    }

    private fun tiklananKategoriUrunleri() {
        val tasinanKategoriString:String? = intent.getStringExtra(Constants.TASINAN_KATEGORI_ADI)
        val kategoriList: KategoriResponseItem? = ObjectUtil.jsonStringToObject(tasinanKategoriString.toString())
        urunList=kategoriList!!.urunler
    }

    private fun initViewModel() {
        urunViewModel= UrunViewModel()
        urunViewModel?.apply {
            urunlerLiveData?.observe(this@UrunActivity, Observer {
                it.run {
                    initRecycleView(urunList!!)
                    ProgressUtil.progressDialogKapat(progressDialog)
                }
            })

            error?.observe(this@UrunActivity, Observer {

                it.run {
                }
            })

            loading?.observe(this@UrunActivity, Observer {

                //Handle loading
            })
        }
    }

    private fun initRecycleView(urunList: List<Urunler>) {
        binding.apply {
            urunAdapter = UrunAdapter(urunList!!, object : ItemClickListener {
                override fun onItemClick(position: Int) {
                    val tiklananUrun: Urunler = urunList.get(position)
                    urunDetayEkraniniAc(tiklananUrun)
                }
            })
            rcvUrunler.adapter = urunAdapter
            rcvUrunler.setLayoutManager( LinearLayoutManager(applicationContext))
        }
    }

    private fun alertListele() {
        val siralama = arrayOf(resources.getString(R.string.ismeGoreArtan),resources.getString(R.string.ismeGoreAzalan))

        AlertUtil.alertListGoster(
            this@UrunActivity,
            resources.getString(R.string.sirala),
            siralama,
            object : ItemClickListener {
                override fun onItemClick(position: Int) {
                    var urunListFiltre:List<Urunler>?=null

                    if(position==AlertUtil.AlertListDialogSecilen.ARTAN_SIRALAMA.gelenDeger){
                        urunList?.let{
                            urunListFiltre = it.sortedBy { it.urunAdi }

                        }
                    }
                    else if (position==AlertUtil.AlertListDialogSecilen.AZALAN_SIRALAMA.gelenDeger){
                        urunList?.let{
                            urunListFiltre = it.sortedByDescending { it.urunAdi }
                        }
                    }

                    if(!urunListFiltre.isNullOrEmpty()){
                        urunAdapter.setData(urunListFiltre!!)
                        urunAdapter.notifyDataSetChanged()
                    }

                    binding.btnSirala.text = siralama.get(position)
                }
            })





    }


    private fun urunDetayEkraniniAc(tiklananUrun: Urunler) {
        val urunIntent = Intent(applicationContext, DetayActivity::class.java)
        val tiklananUrunString:String = ObjectUtil.objectToString(tiklananUrun)
        urunIntent.putExtra(Constants.TASINAN_URUN_ADI, tiklananUrunString)
        startActivity(urunIntent)
    }
}