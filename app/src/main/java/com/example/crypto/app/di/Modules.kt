package com.example.crypto.app.di

import com.example.crypto.data.net.ApiConstants
import com.example.crypto.data.net.mappers.GetHistoryCryptoMapper
import com.example.crypto.data.net.mappers.GetPopularCryptoMapper
import com.example.crypto.data.net.retrofitApi.CryptoApi
import com.example.crypto.presentation.ui.chart.ChartViewModel
import com.example.crypto.presentation.ui.home.HomeViewModel
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val viewModelModule = module {
    viewModel { HomeViewModel(get(), get()) }
    viewModel { ChartViewModel(get(), get()) }
}

val netModule = module {
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient()
    }

    fun provideGsonConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create(
            GsonBuilder()
                .setLenient()
                .create()
        )
    }

    fun provideRetrofit(okHttpClient: OkHttpClient, converterFactory: Converter.Factory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(ApiConstants.API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(converterFactory)
            .build()
    }

    single { provideOkHttpClient() }
    single { provideGsonConverterFactory() }
    single { provideRetrofit(get(), get()) }
}

val netApiModule = module {
    fun provideCryptoApi(retrofit: Retrofit): CryptoApi {
        return retrofit.create(CryptoApi::class.java)
    }

    single { provideCryptoApi(get()) }
}

val repositoryMappersModule = module {
    fun provideGetPopularCryptoMapper(): GetPopularCryptoMapper {
        return GetPopularCryptoMapper()
    }

    single { provideGetPopularCryptoMapper() }
}
val repositoryMappersModule1 = module {
    fun provideGetHistoryCryptoMapper(): GetHistoryCryptoMapper {
        return GetHistoryCryptoMapper()
    }

    single { provideGetHistoryCryptoMapper() }
}