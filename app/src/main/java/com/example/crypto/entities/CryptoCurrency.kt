package com.example.crypto.entities

data class CryptoCurrency(
    var id: String,
    var name: String,
    var symbol: String,
    var priceUsd: Double,
    var percentChange: Double,
    var vwap24Hr: Double
)