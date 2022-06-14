package com.example.crypto.data.net.retrofitApi

import com.example.crypto.data.net.ApiConstants
import com.example.crypto.data.net.dto.GetHistoryCryptoResponse
import com.example.crypto.data.net.dto.GetPopularCryptoListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CryptoApi {
    @GET(ApiConstants.API_ENDPOINT_LATEST_CURRENCY)
    fun getLatestCrypto(@Query("ids") ids: String): Call<GetPopularCryptoListResponse>

    @GET(ApiConstants.API_ENDPOINT_LATEST_CURRENCY)
    fun getLatestCrypto(@Query("ids") ids: String, @Query("limit" ) limit: Int): Call<GetPopularCryptoListResponse>

    @GET(ApiConstants.API_ENDPOINT_HISTORY)
    fun getHistoryCrypto(@Path("id") id: String,
                         @Query("interval") interval: String,
                         @Query("start") start: Long,
                         @Query("end") end: Long): Call<GetHistoryCryptoResponse>

}