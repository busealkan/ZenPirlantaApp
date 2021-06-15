package com.h5190059.zenpirlanta.ui.urun

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.h5190059.zenpirlanta.ItemClickListener
import com.h5190059.zenpirlanta.R
import com.h5190059.zenpirlanta.data.KategoriResponseItem
import com.h5190059.zenpirlanta.data.Urunler
import com.h5190059.zenpirlanta.databinding.ActivityUrunBinding
import com.h5190059.zenpirlanta.util.Constants
import com.h5190059.zenpirlanta.util.ObjectUtil
import com.h5190059.zenpirlanta.util.ProgressUtil
import androidx.lifecycle.Observer
import com.h5190059.zenpirlanta.ui.detay.DetayActivity


class UrunActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUrunBinding
    private var urunViewModel: UrunViewModel? = null
    private lateinit var urunAdapter: UrunAdapter
    private var urunList:List<Urunler>?=null
    private var progressDialog: ProgressDialog?=null
    private var kategoriList: KategoriResponseItem?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        init()
    }

    fun init() {
        binding = ActivityUrunBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tasinanKategoriString:String? = intent.getStringExtra(Constants.TASINAN_KATEGORI_ADI)
        kategoriList = ObjectUtil.jsonStringToObject(tasinanKategoriString.toString())

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

    private fun initViewModel() {
        urunList=kategoriList!!.urunler
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
        val builder = AlertDialog.Builder(this@UrunActivity)
        builder.setTitle(resources.getString(R.string.sirala))
        val siralama = arrayOf(resources.getString(R.string.ismeGoreArtan),resources.getString(R.string.ismeGoreAzalan))
        builder.setItems(siralama) { dialog, pozisyon ->
            when (pozisyon) {
                0 -> {
                    urunList?.let{
                        var urunListFiltre = it.sortedBy { it.urunAdi }
                        urunAdapter.setData(urunListFiltre)
                        urunAdapter.notifyDataSetChanged()
                    }
                }
                1 -> {
                    urunList?.let{
                        var urunListFiltre = it.sortedByDescending { it.urunAdi }
                        urunAdapter.setData(urunListFiltre)
                        urunAdapter.notifyDataSetChanged()
                    }
                }

            }
            binding.btnSirala.text=siralama.get(pozisyon)
        }
        val dialog = builder.create()
        dialog.show()    }

    private fun urunDetayEkraniniAc(tiklananUrun: Urunler) {
        val urunIntent = Intent(applicationContext, DetayActivity::class.java)
        val tiklananUrunString:String = ObjectUtil.objectToString(tiklananUrun)
        urunIntent.putExtra(Constants.TASINAN_URUN_ADI, tiklananUrunString)
        startActivity(urunIntent)
    }
}