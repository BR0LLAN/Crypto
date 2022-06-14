package com.example.crypto.presentation.ui.articles.article

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.devcraft.cryptoapp.R
import com.example.crypto.presentation.ui.home.HomeActivity
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_pager.*

class ArticleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Crypto1)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pager)

        pager_articles.adapter = ViewPagerAdapter(this, intent.getIntExtra("article", 0))
        TabLayoutMediator(tab_layout, pager_articles) { tab, position -> }.attach()

        closeBtn.setOnClickListener {
            val animScale = AnimationUtils.loadAnimation(applicationContext, R.anim.other_btn)
            it.startAnimation(animScale)
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}