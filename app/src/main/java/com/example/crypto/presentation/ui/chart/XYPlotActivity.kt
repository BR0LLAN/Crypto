package com.example.crypto.presentation.ui.chart

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.devcraft.cryptoapp.R
import com.example.crypto.entities.CryptoData
import com.example.crypto.entities.CryptoHistory
import com.example.crypto.presentation.ui.exchangeCrypto.ExchangeScreen
import com.example.crypto.presentation.ui.listCrypto.ListCryptoActivity
import com.github.mikephil.charting.data.Entry
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_about_crypto.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class XYPlotActivity : AppCompatActivity(R.layout.activity_about_crypto) {

    private val vm: ChartViewModel by viewModel()

    lateinit var lineList: ArrayList<Entry>
    private lateinit var chart: LineChartCrypto
    private var strJson: String? = ""
    lateinit var cryptoData: CryptoData
    var gson: Gson = GsonBuilder().serializeNulls().create()
    lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.statusBarColor = this.getColor(R.color.statusBarColor)
            window.statusBarColor = this.getColor(R.color.colorForOtherScreenStatusBar)
        }

        setSelectCryptoItem()
        observeData()
        initListeners()
    }

    private fun initListeners() {
        back_btn_about_crypto.setOnClickListener {
            val animScale = AnimationUtils.loadAnimation(applicationContext, R.anim.other_btn)
            it.startAnimation(animScale)
            onBackPressed()
            finish()
        }
        btn_exchange.setOnClickListener {
            val animScale = AnimationUtils.loadAnimation(applicationContext, R.anim.exchange_btn)
            it.startAnimation(animScale)
            val intent = Intent(this, ExchangeScreen::class.java)
            intent.putExtra("KEY", 1)
            startActivity(intent)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setSelectCryptoItem() {
        //Получение идентификатора криптовалюты
        strJson = intent.getStringExtra("SELECT_CRYPTO")
        preferences = baseContext.getSharedPreferences("CRYPTO", Context.MODE_PRIVATE)
        //Проверка переменной strJson на null
        if (strJson == null) {
            strJson = preferences.getString("id", "").toString()
        }

        preferences.edit().putString("id", strJson).apply()

        cryptoData = gson.fromJson(preferences.getString(strJson, ""), CryptoData::class.java)
        val resourceID = this.resources.getIdentifier(
            "ic_" + cryptoData.crypto_id.replace("-", "_"),
            "drawable", baseContext.packageName
        )
        icon_crypto_about.setImageResource(resourceID)
        symbols.text = cryptoData.crypto_symbol
        name_crypto.text = cryptoData.crypto_name
        if (cryptoData.crypto_percentChange < 0.0) {
            percent_change.text = String.format("%.2f", cryptoData.crypto_percentChange) + "%"
            percent_change.setTextColor(Color.parseColor("#f80000"))
        } else {
            percent_change.text =
                "+" + String.format("%.2f", cryptoData.crypto_percentChange) + "%"
            percent_change.setTextColor(Color.parseColor("#45B68D"))
        }
        crypto_value.text = "$" + String.format("%.2f", cryptoData.crypto_priceUsd)
        symbols_crypto_select.text = cryptoData.crypto_symbol

        vm.loadHistoryData(cryptoData.crypto_id)
    }

    private fun observeData() {
        vm.chartHistory.observe(this, {
            drawChart(it)
        })
    }

    private fun drawChart(data: MutableList<CryptoHistory>) {
        lineList = ArrayList()
        data.forEach {
            lineList.add(Entry(it.time.toFloat(), it.priceUsd))
        }

        chart = LineChartCrypto(lineChart)
        chart.initLineDataSet(lineList)
        chart.setLineData()
        chart.initLineChart()
    }
}



