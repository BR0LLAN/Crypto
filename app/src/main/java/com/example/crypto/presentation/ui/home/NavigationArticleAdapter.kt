package com.example.crypto.presentation.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.devcraft.cryptoapp.R
import kotlinx.android.synthetic.main.single_navigation_article_home_screen.view.*
class NavigationArticleAdapter(
    private val listenerArticleItem: OnItemClickListenerArticle,
):
    RecyclerView.Adapter<NavigationArticleAdapter.NavigationArticleViewHolder>(){

    private var items : MutableList<Int> = arrayListOf(
        R.string.btn_what_to_crypto,
        R.string.btn_HowtoStartTradingBitcoin,
        R.string.btn_3TradingStrategies)

        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavigationArticleViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.single_navigation_article_home_screen, parent, false)

        return  NavigationArticleViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NavigationArticleViewHolder, position: Int) {
        holder.bind(items[position])
    }

    inner class NavigationArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener{
        @SuppressLint("SetTextI18n")
        fun bind(item: Int){
            itemView.run {
                text_nav_article.text =  resources.getString(item)
            }
        }
        init{
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION){
                listenerArticleItem.onItemClick(position)
            }
        }
    }
    interface OnItemClickListenerArticle{
        fun onItemClick(position: Int)
    }
}