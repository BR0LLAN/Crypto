package com.example.crypto.data.net.dto

data class GetPopularCryptoListResponse (
	val timestamp: Double,
	val data : List<Data>

){
	data class Data(
		val id: String,
		val rank: Int,
		val name: String,
		val symbol: String,
		val supply: Double,
		val maxSupply: Double,
		val marketCapUsd: Double,
		val volumeUsd24Hr: Double,
		val priceUsd: Double,
		val changePercent24Hr: Double,
		val vwap24Hr: Double,
		val explorer: String,
		val idH: String,
		val interval: Int
	)
}