package com.example.crypto.presentation.ui.exchangeCrypto

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.devcraft.cryptoapp.R
import com.example.crypto.entities.CryptoData
import com.example.crypto.presentation.ui.chart.XYPlotActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_exchange_screen.*

class ExchangeScreen : AppCompatActivity() {

    private lateinit var cryptoDataFrom: CryptoData
    private lateinit var preferences: SharedPreferences
    private var strJsonFrom: String? = ""
    private var strJsonTo: String? = ""
    private var fromPrice: Double = 0.0
    private var toPrice: Double = 0.0
    private var getInput: String? = ""
    private var get: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_Crypto)
        setContentView(R.layout.activity_exchange_screen)
        preferences = baseContext.getSharedPreferences("CRYPTO", Context.MODE_PRIVATE)

        initViews()
        initListeners()
    }

    private fun initViews() {
        cryptoForConvertFrom()
        cryptoForConvertTo()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.statusBarColor = this.getColor(R.color.colorForOtherScreenStatusBar)
        }
    }

    private fun initListeners() {

        back_btn_exchange_list_crypto.setOnClickListener {
            val animScale = AnimationUtils.loadAnimation(applicationContext, R.anim.other_btn)
            it.startAnimation(animScale)
            preferences.edit().remove("GET_VALUE_COUNTER").apply()
            startActivity(Intent(this, XYPlotActivity::class.java))
            finish()
        }
        convert_coin.setOnClickListener {
            val intent = Intent(this, ExchangeCrypto::class.java)
            intent.putExtra("KEY", 1)
            startActivity(intent)
            finish()
        }
        convert_coin1.setOnClickListener {
            val intent = Intent(this, ExchangeCrypto::class.java)
            intent.putExtra("KEY", 2)
            startActivity(intent)
            finish()
        }

        getInputExchange()


        btn_change.setOnClickListener {
            val animScale =
                AnimationUtils.loadAnimation(applicationContext, R.anim.exchange_stroke_btn)
            it.startAnimation(animScale)
            val fromChange = preferences.getString("FROM", "")
            val toChange = preferences.getString("TO", "")
            preferences.edit().putString("TO", fromChange).apply()
            preferences.edit().putString("FROM", toChange).apply()
            changeConvertTo()
            changeConvertFrom()
            input_coin_exchange.text = input_coin_exchange.text
        }
    }

    @SuppressLint("SetTextI18n")
    private fun cryptoForConvertFrom() {
        if (intent.getIntExtra("KEY", 0) == 1) {
            println(intent.getIntExtra("KEY", 0))
            if (intent.getStringExtra("ID_CRYPTO") == null) {
                strJsonFrom = preferences.getString("id", "")
            } else {
                strJsonFrom = intent.getStringExtra("ID_CRYPTO")
            }
            preferences.edit().putString("FROM", strJsonFrom).apply().toString()
        }
        changeConvertFrom()
    }

    private fun changeConvertFrom() {
        val strNew = preferences.getString("FROM", "")
        cryptoDataFrom = Gson().fromJson(preferences.getString(strNew, ""), CryptoData::class.java)
        val resourceID = this.resources.getIdentifier(
            "ic_" + cryptoDataFrom.crypto_id.replace("-", "_"),
            "drawable", baseContext.packageName
        )
        icon_crypto_exchange_from.setImageResource(resourceID)
        symbol_exchange_from.text = cryptoDataFrom.crypto_symbol
        mySelectCoin_from.text = cryptoDataFrom.crypto_name
        fromPrice = cryptoDataFrom.crypto_priceUsd
    }

    @SuppressLint("SetTextI18n")
    private fun cryptoForConvertTo() {
        if (intent.getIntExtra("KEY", 0) == 2) {
            if (intent.getStringExtra("ID_CRYPTO") == null) {
                strJsonTo = preferences.getString("id", "")
            } else {
                strJsonTo = intent.getStringExtra("ID_CRYPTO")
            }
        } else {
            if (intent.getStringExtra("ID_CRYPTO") == null && preferences.getString("TO","") == "") {
                println(intent.getStringExtra("ID_CRYPTO")+"""\\\\\\""")
                strJsonTo = preferences.getString("id", "")
            } else {
                strJsonTo = preferences.getString("TO", "")
            }
        }
        preferences.edit().putString("TO", strJsonTo).apply().toString()
        changeConvertTo()
    }

    private fun changeConvertTo() {
        val strNew = preferences.getString("TO", "")
        cryptoDataFrom = Gson().fromJson(preferences.getString(strNew, ""), CryptoData::class.java)
        val resourceID = this.resources.getIdentifier(
            "ic_" + cryptoDataFrom.crypto_id.replace("-", "_"),
            "drawable", baseContext.packageName
        )
        icon_crypto_exchange_to.setImageResource(resourceID)
        symbol_exchange_to.text = cryptoDataFrom.crypto_symbol
        mySelectCoin_to.text = cryptoDataFrom.crypto_name
        toPrice = cryptoDataFrom.crypto_priceUsd
    }

    private fun getInputExchange() {
        if (preferences.getString("GET_VALUE_COUNTER", "")!!.isNotEmpty()) {
            get = preferences.getString("GET_VALUE_COUNTER", "")
            counter_coin.text = get
            out_price.text =
                String.format("%.2f", ((get.toString().toDouble() * fromPrice) / toPrice))
            input_coin_exchange.setText(get)
        }
        input_coin_exchange.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            @SuppressLint("SetTextI18n")
            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                if (s.isNotEmpty()) {
                    getInput =
                        String.format("%.2f", ((s.toString().toDouble() * fromPrice) / toPrice))
                    out_price.text = getInput
                    counter_coin.text = s
                    preferences.edit().putString("GET_VALUE_COUNTER", s.toString()).apply()
                } else {
                    out_price.text = "0.00"
                }
            }
        })
    }
    override fun onBackPressed() {
        super.onBackPressed()
        preferences.edit().remove("GET_VALUE_COUNTER").apply()
    }
}


