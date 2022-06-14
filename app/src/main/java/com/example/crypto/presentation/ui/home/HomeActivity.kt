package com.example.crypto.presentation.ui.home

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.devcraft.cryptoapp.R
import com.example.crypto.presentation.ui.articles.article.ArticleActivity
import com.example.crypto.presentation.ui.chart.XYPlotActivity
import com.example.crypto.presentation.ui.listCrypto.ListCryptoActivity
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeActivity : AppCompatActivity(R.layout.activity_home), NavigationArticleAdapter.OnItemClickListenerArticle, PopularCryptoAdapter.OnItemClickListenerPopularCrypto {

    private val vm: HomeViewModel by viewModel()
    private val adapterPopularCrypto = PopularCryptoAdapter(this)
    private val adapterNavigationArticle = NavigationArticleAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Crypto)
        super.onCreate(savedInstanceState)
        initViews()
        initListeners()
        initObservers()
        vm.loadData()
    }

    private fun initViews() {

        //RecyclerView for popular list crypto
        view_list_popular_crypto.adapter = adapterPopularCrypto
        view_list_popular_crypto.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        //RecyclerView for article navigation
        r_navigation_articles.adapter = adapterNavigationArticle
        r_navigation_articles.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.statusBarColor = this.getColor(R.color.statusBarColor)
        }
    }

    private fun initListeners() {
        btn_see_all_crypto.setOnClickListener{
            val animScale = AnimationUtils.loadAnimation(applicationContext, R.anim.other_btn)
            it.startAnimation(animScale)
            startActivity(Intent(this, ListCryptoActivity::class.java))
        }
    }

    private fun initObservers() {
        vm.popularCurrencies.observe(this, {
            adapterPopularCrypto.items = it
        })
        vm.getPopularCurrenciesFailure.observe(this, {
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onItemClick(position: Int) {
        val intent = Intent(this, ArticleActivity::class.java)
        intent.putExtra("article", position+1)
        startActivity(intent)

    }

    override fun onItemClickPopularCrypto(position: Int, id_cr: String) {
        val intent = Intent(this, XYPlotActivity::class.java)
        intent.putExtra("SELECT_CRYPTO", id_cr)
        startActivity(intent)
    }

    override fun onRestart() {
        super.onRestart()
        vm.loadData()
    }

}