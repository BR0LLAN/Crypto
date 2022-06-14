package com.example.crypto.entities

import kotlinx.serialization.Serializable

@Serializable
data class CryptoData (
    //@SerializedName("cryptoId")
    val crypto_id: String,
   // @SerializedName("cryptoName")
    val crypto_name: String,
    //@SerializedName("cryptoSymbol")
    val crypto_symbol: String,
    //@SerializedName("cryptoPriceUsd")
    val crypto_priceUsd: Double,
    //@SerializedName("cryptoPercentChange")
    val crypto_percentChange: Double
)
