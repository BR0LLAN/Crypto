package com.example.crypto.presentation.ui.exchangeCrypto

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.devcraft.cryptoapp.R
import com.example.crypto.presentation.ui.home.HomeViewModel
import kotlinx.android.synthetic.main.activity_exchange_crypto_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class ExchangeCrypto : AppCompatActivity(),
    ExchangeViewCryptoAdapter.OnItemClickListenerExchangeList {

    private val vm: HomeViewModel by viewModel()
    private val adapterExchangeListCrypto = ExchangeViewCryptoAdapter(this)
    private var resultInt: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Crypto)
        setContentView(R.layout.activity_exchange_crypto_list)

        resultInt = intent.getIntExtra("KEY",0)
        println(resultInt)

        initViews()
        initListeners()
        initObservers()
        vm.loadData1()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.statusBarColor = this.getColor(R.color.colorForOtherScreenStatusBar)
        }
    }

    private fun initListeners() {
        back_btn_exchange_list_c.setOnClickListener{
            val animScale = AnimationUtils.loadAnimation(applicationContext, R.anim.other_btn)
            it.startAnimation(animScale)
            startActivity(Intent(this, ExchangeScreen::class.java))
            finish()
        }
    }

    private fun initViews() {
        recycler_list_crypto_exchange.adapter = adapterExchangeListCrypto
        recycler_list_crypto_exchange.layoutManager = LinearLayoutManager(
            this, LinearLayoutManager.VERTICAL, false)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.statusBarColor = this.getColor(R.color.colorForOtherScreenStatusBar)
        }
    }
    private fun initObservers() {
        vm.listCurrencies.observe(this, {
            adapterExchangeListCrypto.items = it
        })
        vm.getListCurrenciesFailure.observe(this, {
            Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onItemClickList(position: Int, id_cr: String) {
        val intent = Intent(this, ExchangeScreen::class.java)
        intent.putExtra("KEY", resultInt)
        intent.putExtra("ID_CRYPTO", id_cr)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, ExchangeScreen::class.java))
        finish()
    }
}