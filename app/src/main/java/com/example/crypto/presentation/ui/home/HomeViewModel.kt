package com.example.crypto.presentation.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.crypto.app.Constants
import com.example.crypto.data.net.dto.GetHistoryCryptoResponse
import com.example.crypto.data.net.dto.GetPopularCryptoListResponse
import com.example.crypto.data.net.mappers.GetPopularCryptoMapper
import com.example.crypto.data.net.retrofitApi.CryptoApi
import com.example.crypto.entities.CryptoCurrency
import com.example.crypto.presentation.common.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel(
    private val cryptoApi: CryptoApi,
    private val getListCryptoMapper: GetPopularCryptoMapper
) : ViewModel() {

    val popularCurrencies: MutableLiveData<MutableList<CryptoCurrency>> =
        MutableLiveData(mutableListOf())
    val getPopularCurrenciesFailure: SingleLiveEvent<Throwable> = SingleLiveEvent()

    val listCurrencies: MutableLiveData<MutableList<CryptoCurrency>> =
        MutableLiveData(mutableListOf())

    val getListCurrenciesFailure: SingleLiveEvent<Throwable> = SingleLiveEvent()

    fun loadData() {
        cryptoApi.getLatestCrypto(Constants.IDS.joinToString(separator= ","), Constants.HOME_PAGE_CURRENCIES_LIMIT)
            .enqueue(object : Callback<GetPopularCryptoListResponse> {
                override fun onResponse(call: Call<GetPopularCryptoListResponse>, response: Response<GetPopularCryptoListResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        popularCurrencies.postValue(getListCryptoMapper.mapDtoToEntity(response.body()!!))

                    } else {
                        getPopularCurrenciesFailure.postValue(Error("something get wrong, response code: ${response.code()}"))
                    }
                }

                override fun onFailure(call: Call<GetPopularCryptoListResponse>, t: Throwable) {
                    getPopularCurrenciesFailure.postValue(t)
                }
            })
    }
    fun loadData1() {
        cryptoApi.getLatestCrypto(Constants.IDS.joinToString(separator= ","))
            .enqueue(object : Callback<GetPopularCryptoListResponse> {
                override fun onResponse(
                    call: Call<GetPopularCryptoListResponse>,
                    response: Response<GetPopularCryptoListResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        listCurrencies.postValue(getListCryptoMapper.mapDtoToEntity(response.body()!!))
                    } else {
                        getListCurrenciesFailure.postValue(Error("something get wrong, response code: ${response.code()}"))
                    }
                }

                override fun onFailure(call: Call<GetPopularCryptoListResponse>, t: Throwable) {
                    getListCurrenciesFailure.postValue(t)
                }
            })
    }
}
