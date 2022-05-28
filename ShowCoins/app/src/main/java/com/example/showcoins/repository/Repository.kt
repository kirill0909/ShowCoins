package com.example.showcoins.repository

import com.example.showcoins.api.RetrofitInstance
import com.example.showcoins.model.Coin

class Repository {

    /*
    *This method return list/30.
    * Because original size 9000+ elements
     */
    suspend fun getCoins(): List<Coin> {
        return RetrofitInstance.api.getCoins().let { it.subList(0, it.size / 30) }
    }
}