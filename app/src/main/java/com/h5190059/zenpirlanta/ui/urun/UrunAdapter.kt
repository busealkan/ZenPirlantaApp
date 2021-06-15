package com.h5190059.zenpirlanta.ui.urun

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.h5190059.zenpirlanta.ItemClickListener
import com.h5190059.zenpirlanta.data.Urunler
import com.h5190059.zenpirlanta.databinding.CardviewUrunBinding
import com.h5190059.zenpirlanta.resimCek

class UrunAdapter (
    var urunler: List<Urunler>,
    var onItemClickListener: ItemClickListener ) : RecyclerView.Adapter<UrunAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: CardviewUrunBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardviewUrunBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return urunler.size
    }

    fun setData(siralanmisUrunler:List<Urunler>) {
        urunler = siralanmisUrunler
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder){
            binding.apply {
                binding.txtUrunAd.text = urunler[position].urunAdi
                binding.txtUrunFiyat.text = urunler[position].urunFiyat
                imgUrun.resimCek(urunler[position].urunResim)

                urunCard.setOnClickListener{
                    onItemClickListener.onItemClick(position)
                }
            }
        }
    }
}