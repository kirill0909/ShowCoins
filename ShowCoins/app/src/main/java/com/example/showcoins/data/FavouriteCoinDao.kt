package com.example.showcoins.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.Delete
import androidx.room.OnConflictStrategy
import com.example.showcoins.model.FavouriteCoin

@Dao
interface FavouriteCoinDao {

    @Query("SELECT * FROM favourite_coin_table")
    fun getAllFavouriteCoins(): LiveData<List<FavouriteCoin>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCoinToFavourite(favouriteCoin: FavouriteCoin)

    @Query("SELECT EXISTS(SELECT * FROM favourite_coin_table WHERE coinName = :coinName)")
    suspend fun isFavouriteCoinExist(coinName: String): Boolean

    @Delete
    suspend fun remove(favouriteCoin: FavouriteCoin)
}
