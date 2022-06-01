package com.example.showcoins.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.showcoins.data.AppDatabase
import com.example.showcoins.model.FavouriteCoin
import com.example.showcoins.repository.FavouriteCoinRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import com.example.showcoins.adapters.FavouriteCoinsListAdapter

class FavouriteCoinViewModel(application: Application) : AndroidViewModel(application) {

    private var _favouriteListCoins: LiveData<List<FavouriteCoin>>
    val favouriteListCoins: LiveData<List<FavouriteCoin>>
        get() = _favouriteListCoins

    private val repository: FavouriteCoinRepository
    val title = "Favourites coins"

    init {
        val favouriteCoinDao = AppDatabase.getDatabase(application).favouriteCoinDao()
        repository = FavouriteCoinRepository(favouriteCoinDao)
        _favouriteListCoins = repository.allFavouriteCoins
    }

    fun sortedByAZ(adapter: FavouriteCoinsListAdapter) {
        val sortedList =
            _favouriteListCoins.value.orEmpty().toMutableList().sortedBy { it.coinName }
        adapter.setData(sortedList)

    }

    fun sortedByZA(adapter: FavouriteCoinsListAdapter) {
        adapter.setData(_favouriteListCoins.value.orEmpty().toMutableList().sortedBy { it.coinName }
            .reversed())
    }

    fun sortedByHighValue(adapter: FavouriteCoinsListAdapter) {
        adapter.setData(
            _favouriteListCoins.value.orEmpty().toMutableList().sortedBy { it.coinPrice }.reversed()
        )
    }

    fun sortedByLowValue(adapter: FavouriteCoinsListAdapter) {
        adapter.setData(
            _favouriteListCoins.value.orEmpty().toMutableList().sortedBy { it.coinPrice }
        )
    }

    fun addCoinToFavourite(favouriteCoin: FavouriteCoin) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCoinToFavourite(favouriteCoin)
        }
    }

    /*
    *This method check exists coin inside favourite list,
    * if exists return true else false
     */
    fun isFavouriteCoinExist(coinName: String): Boolean = runBlocking {
        repository.isFavouriteCoinExist(coinName)
    }


    fun remove(favouriteCoin: FavouriteCoin) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.remove(favouriteCoin)
        }
    }

}
