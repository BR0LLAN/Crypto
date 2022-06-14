package com.example.crypto.presentation.ui.exchangeCrypto

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devcraft.cryptoapp.R
import com.example.crypto.entities.CryptoCurrency
import com.example.crypto.entities.CryptoData
import com.google.gson.Gson
import kotlinx.android.synthetic.main.single_view_list_crypto.view.*
import kotlinx.android.synthetic.main.single_view_popular_crypto.view.name_crypto
import kotlinx.android.synthetic.main.single_view_popular_crypto.view.symbols

class ExchangeViewCryptoAdapter(private val listenerExchangeListItem: OnItemClickListenerExchangeList)
    : RecyclerView.Adapter<ExchangeViewCryptoAdapter.ExchangeListViewCryptoViewHolder>(){

    var items : MutableList<CryptoCurrency> = mutableListOf()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    lateinit var cryptoJson: String
    lateinit var data: CryptoData

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExchangeListViewCryptoViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.single_view_list_crypto_exchange, parent, false)

        return  ExchangeListViewCryptoViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ExchangeViewCryptoAdapter.ExchangeListViewCryptoViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class ExchangeListViewCryptoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        @SuppressLint("SetTextI18n")
        fun bind(item: CryptoCurrency) {
            itemView.run {
                val resourceID = this.resources.getIdentifier(
                    "ic_"+item.id.replace("-","_"),
                    "drawable", context.packageName)

                icon_crypto_list.setImageResource(resourceID)

                name_crypto.text = item.name
                symbols.text = "("+item.symbol+")"

                data = CryptoData(item.id,item.name,item.symbol,
                    item.priceUsd,item.percentChange)
            }
            cryptoJson = Gson().toJson(data)
            val preferences = itemView.context.getSharedPreferences("CRYPTO", Context.MODE_PRIVATE)
            preferences.edit().putString(item.id, cryptoJson).apply()
        }

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            val id_cr = items[position].id
            if (position != RecyclerView.NO_POSITION){
                listenerExchangeListItem.onItemClickList(position, id_cr)
            }
        }
    }

    interface OnItemClickListenerExchangeList{
        fun onItemClickList(position: Int, id_cr: String, )
    }

}
