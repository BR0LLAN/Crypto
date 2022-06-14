package com.example.crypto.presentation.ui.articles.article

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.devcraft.cryptoapp.R
import kotlinx.android.synthetic.main.fragment_article.*


class ArticleFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_article, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let {
            article_title.text = it.getString("title").toString()
            article_image.setImageResource(it.getInt("img_resource"))
            article_content.text = it.getString("textContent").toString()
        }
    }


}