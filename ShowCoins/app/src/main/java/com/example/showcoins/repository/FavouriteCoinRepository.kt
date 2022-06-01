package com.example.showcoins.repository

import com.example.showcoins.data.FavouriteCoinDao
import com.example.showcoins.model.FavouriteCoin

class FavouriteCoinRepository(private val favouriteCoinDao: FavouriteCoinDao) {

    val allFavouriteCoins = favouriteCoinDao.getAllFavouriteCoins()

    suspend fun addCoinToFavourite(favouriteCoin: FavouriteCoin) {
        favouriteCoinDao.addCoinToFavourite(favouriteCoin)
    }

    suspend fun isFavouriteCoinExist(coinName: String): Boolean {
        return favouriteCoinDao.isFavouriteCoinExist(coinName)
    }

    suspend fun remove(favouriteCoin: FavouriteCoin) {
        favouriteCoinDao.remove(favouriteCoin)
    }


}
