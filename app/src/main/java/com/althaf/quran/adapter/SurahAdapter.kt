package com.althaf.quran.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.althaf.quran.databinding.ItemAyahBinding
import com.althaf.quran.core.di.domain.model.Ayah
import com.althaf.quran.core.di.domain.model.QuranEdition

class SurahAdapter: RecyclerView.Adapter<SurahAdapter.MyViewHolder>() {
    private val listAyah = ArrayList<Ayah>()
    private val quranEditionItem = ArrayList<QuranEdition>()
    private var onItemClickCallback : OnItemClickCallback? = null

    class MyViewHolder(val binding: ItemAyahBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        ItemAyahBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun getItemCount(): Int = listAyah.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val dataAyahs = listAyah[position]
        val quranAudio = quranEditionItem[1].ayahs?.get(position)
        val quranIndonesia = quranEditionItem[2].ayahs?.get(position)

        holder.binding.apply {
            itemNumberAyah.text = dataAyahs.numberInSurah.toString()
            itemAyah.text = dataAyahs.text
            itemTranslation.text = quranIndonesia?.text
            this.root.setOnClickListener {
                quranAudio?.let { data -> onItemClickCallback?.onItemClicked(data) }
            }
        }
    }

    fun setData(dataAyahs: List<Ayah>?, dataQuranEditionItem: List<QuranEdition>?) {
        if (dataAyahs == null || dataQuranEditionItem == null) return
        listAyah.clear()
        listAyah.addAll(dataAyahs)
        quranEditionItem.clear()
        quranEditionItem.addAll(dataQuranEditionItem)
    }

    fun setOnItemClicked(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Ayah)
    }
}