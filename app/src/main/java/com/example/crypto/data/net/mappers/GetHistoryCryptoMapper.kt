package com.example.crypto.data.net.mappers

import com.example.crypto.data.net.dto.GetHistoryCryptoResponse
import com.example.crypto.entities.CryptoHistory

class GetHistoryCryptoMapper {
    fun mapDtoToEntity(dto: GetHistoryCryptoResponse): MutableList<CryptoHistory> {
        return dto.data.map {
            CryptoHistory(
                it.priceUsd,
                it.time,
                it.date
            )
        }.toMutableList()
    }
}