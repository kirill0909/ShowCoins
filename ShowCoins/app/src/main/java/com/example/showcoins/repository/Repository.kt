package com.example.showcoins.repository

import com.example.showcoins.api.RetrofitInstance
import com.example.showcoins.model.Coin
import retrofit2.Response

class Repository {

    suspend fun getCoins(): Response<List<Coin>> {
        return RetrofitInstance.api.getCoins()
    }
}