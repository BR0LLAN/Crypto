package com.example.crypto.presentation.ui.listCrypto

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.devcraft.cryptoapp.R
import com.example.crypto.presentation.ui.chart.XYPlotActivity
import com.example.crypto.presentation.ui.home.HomeActivity
import com.example.crypto.presentation.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.activity_crypto_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListCryptoActivity : AppCompatActivity(R.layout.activity_crypto_list), ListViewCryptoAdapter.OnItemClickListenerList {

    private val vm: HomeViewModel by viewModel()
    private val adapterListCrypto = ListViewCryptoAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_Crypto)
        super.onCreate(savedInstanceState)
        initViews()
        initListeners()
        initObservers()
        vm.loadData1()

    }

    private fun initListeners() {
        back_btn_list_crypto.setOnClickListener{
            val animScale = AnimationUtils.loadAnimation(applicationContext, R.anim.other_btn)
            it.startAnimation(animScale)
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun initViews() {
        recycler_list_crypto.adapter = adapterListCrypto
        recycler_list_crypto.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.statusBarColor = this.getColor(R.color.statusBarColor)
        }
    }
    private fun initObservers() {
        vm.listCurrencies.observe(this, {
            adapterListCrypto.items = it
        })
        vm.getListCurrenciesFailure.observe(this, {
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onItemClickList(position: Int, id_cr: String) {
        val intent = Intent(this, XYPlotActivity::class.java)
        intent.putExtra("SELECT_CRYPTO", id_cr)
        startActivity(intent)
    }
}



