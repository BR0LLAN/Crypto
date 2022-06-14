package com.example.crypto.data.net.dto

data class GetHistoryCryptoResponse (
	val timestamp: Long,
	val data : List<Data>

){
	data class Data(
		val priceUsd: Float,
		val time: Long,
		val date: String
	)
}