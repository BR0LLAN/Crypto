package com.example.crypto.data.net.mappers

import com.example.crypto.data.net.dto.GetPopularCryptoListResponse
import com.example.crypto.entities.CryptoCurrency
import com.google.gson.Gson

class GetPopularCryptoMapper {
    fun mapDtoToEntity(dto: GetPopularCryptoListResponse): MutableList<CryptoCurrency> {
        return dto.data.map {
            CryptoCurrency(
                it.id,
                it.name,
                it.symbol,
                it.priceUsd,
                it.changePercent24Hr,
                it.vwap24Hr
            )
        }.toMutableList()
    }
}