package com.example.crypto.presentation.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devcraft.cryptoapp.R
import com.example.crypto.entities.CryptoCurrency
import com.example.crypto.entities.CryptoData
import com.google.gson.Gson
import kotlinx.android.synthetic.main.single_view_popular_crypto.view.*
import kotlinx.android.synthetic.main.single_view_popular_crypto.view.crypto_value
import kotlinx.android.synthetic.main.single_view_popular_crypto.view.name_crypto
import kotlinx.android.synthetic.main.single_view_popular_crypto.view.percent_change
import kotlinx.android.synthetic.main.single_view_popular_crypto.view.symbols

class PopularCryptoAdapter(private val listenerPopularItem: OnItemClickListenerPopularCrypto)
    : RecyclerView.Adapter<PopularCryptoAdapter.PopularCryptoViewHolder>(){

    var items : MutableList<CryptoCurrency> = mutableListOf()
    @SuppressLint("NotifyDataSetChanged")
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    lateinit var data: CryptoData
    lateinit var cryptoJson: String

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularCryptoViewHolder {
        val itemView = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.single_view_popular_crypto, parent, false)
        return  PopularCryptoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: PopularCryptoViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class PopularCryptoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        @SuppressLint("SetTextI18n")
        fun bind(item: CryptoCurrency){
            itemView.run {
                val resourceID = this.resources.getIdentifier(
                    "ic_" + item.id.replace("-", "_"),
                    "drawable", context.packageName)

                icon_crypto.setImageResource(resourceID)
                name_crypto.text = item.name
                symbols.text = "("+item.symbol+")"
                crypto_value.text = "$" + String.format("%.2f", item.priceUsd)
                if(item.percentChange < 0.0){
                    percent_change.text = String.format("%.2f", item.percentChange) + "%"
                    percent_change.setTextColor(Color.parseColor("#f80000"))
                }
                else{
                    percent_change.text = "+" + String.format("%.2f", item.percentChange) + "%"
                    percent_change.setTextColor(Color.parseColor("#45B68D"))
                }
                data = CryptoData(item.id,item.name,item.symbol,
                    item.priceUsd,item.percentChange)
            }
            cryptoJson = Gson().toJson(data)
            val preferences = itemView.context.getSharedPreferences("CRYPTO", Context.MODE_PRIVATE)
            preferences.edit().putString(item.id, cryptoJson).apply()
        }
        init{
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition

            val id_cr = items[position].id
            if (position != RecyclerView.NO_POSITION){
                listenerPopularItem.onItemClickPopularCrypto(position, id_cr)
            }
        }

    }
    interface OnItemClickListenerPopularCrypto{
        fun onItemClickPopularCrypto(position: Int, id_cr: String)
    }
}