package com.h5190059.zenpirlanta.ui.kategori

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.h5190059.zenpirlanta.ItemClickListener
import com.h5190059.zenpirlanta.data.KategoriResponseItem
import com.h5190059.zenpirlanta.databinding.CardviewKategoriBinding
import com.h5190059.zenpirlanta.resimCek

class KategoriAdapter (
    var kategoriler: List<KategoriResponseItem>,
    var onItemClickListener: ItemClickListener ) : RecyclerView.Adapter<KategoriAdapter.ViewHolder>() {

        inner class ViewHolder(val binding: CardviewKategoriBinding) : RecyclerView.ViewHolder(binding.root)
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val binding = CardviewKategoriBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(binding)
        }

        override fun getItemCount(): Int {
            return kategoriler.size
        }

        fun setData(filtreliKategori:List<KategoriResponseItem>) {
            kategoriler = filtreliKategori
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            with(holder){
                binding.apply {
                    binding.txtKategoriAd.text = kategoriler[position].kategoriAdi
                    imgKategori.resimCek(kategoriler[position].kategoriResimUrl)

                    kategoriCard.setOnClickListener{
                        onItemClickListener.onItemClick(position)
                    }
                }
            }
        }
}