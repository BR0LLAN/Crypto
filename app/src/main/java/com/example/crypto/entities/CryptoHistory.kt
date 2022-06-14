package com.example.crypto.entities

data class CryptoHistory(
    var priceUsd: Float,
    var time: Long,
    var date: String
)