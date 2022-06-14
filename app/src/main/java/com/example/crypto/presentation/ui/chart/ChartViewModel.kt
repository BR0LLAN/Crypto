package com.example.crypto.presentation.ui.chart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.crypto.data.net.dto.GetHistoryCryptoResponse
import com.example.crypto.data.net.mappers.GetHistoryCryptoMapper
import com.example.crypto.data.net.retrofitApi.CryptoApi
import com.example.crypto.entities.CryptoHistory
import com.example.crypto.presentation.common.SingleLiveEvent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class ChartViewModel(
    private val cryptoApi: CryptoApi,
    private val getHistoryCryptoMapper: GetHistoryCryptoMapper
) : ViewModel() {

    val chartHistory: MutableLiveData<MutableList<CryptoHistory>> =
        MutableLiveData(mutableListOf())

    val getHistoryFailure: SingleLiveEvent<Throwable> = SingleLiveEvent()

    fun loadHistoryData(id: String) {
        val cEndDate = Calendar.getInstance()
        val cStartDate = Calendar.getInstance()
        cStartDate.add(Calendar.DAY_OF_YEAR, -12)

        cryptoApi.getHistoryCrypto(id, "d1", cStartDate.timeInMillis, cEndDate.timeInMillis)
            .enqueue(object : Callback<GetHistoryCryptoResponse> {
                override fun onResponse(
                    call: Call<GetHistoryCryptoResponse>,
                    response: Response<GetHistoryCryptoResponse>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        chartHistory.postValue(getHistoryCryptoMapper.mapDtoToEntity(response.body()!!))
                    } else {
                        getHistoryFailure.postValue(Error("something get wrong, response code: ${response.code()}"))
                    }
                }

                override fun onFailure(call: Call<GetHistoryCryptoResponse>, t: Throwable) {
                    getHistoryFailure.postValue(t)
                }
            })
    }
}
