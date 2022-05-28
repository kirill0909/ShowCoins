package com.example.showcoins.api

import com.example.showcoins.model.Coin
import retrofit2.Response
import retrofit2.http.GET

interface CoinApi {

    @GET("v1/ticker")
    suspend fun getCoins(): Response<List<Coin>>
}