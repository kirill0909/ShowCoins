package com.example.showcoins.viewmodel

import android.app.Application
import androidx.lifecycle.*
import com.example.showcoins.data.AppDatabase
import com.example.showcoins.model.FavouriteCoin
import com.example.showcoins.repository.FavouriteCoinRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import com.example.showcoins.adapters.FavouriteCoinsListAdapter

class FavouriteCoinViewModel(application: Application) : AndroidViewModel(application) {

    private var _favouriteListCoins: MutableLiveData<List<FavouriteCoin>>
    val favouriteListCoins: LiveData<List<FavouriteCoin>>
        get() = _favouriteListCoins

    private val repository: FavouriteCoinRepository
    val title = "Favourites coins"

    init {
        val favouriteCoinDao = AppDatabase.getDatabase(application).favouriteCoinDao()
        repository = FavouriteCoinRepository(favouriteCoinDao)
        _favouriteListCoins = repository.allFavouriteCoins.toMutableLiveData()
    }

    /*
    *This method extends LiveData and return MutableLiveData
     */
    private fun <T> LiveData<T>.toMutableLiveData(): MutableLiveData<T> {
        val mediatorLiveData = MediatorLiveData<T>()
        mediatorLiveData.addSource(this) {
            mediatorLiveData.value = it
        }
        return mediatorLiveData
    }

    fun sortedByAZ() {
        _favouriteListCoins.postValue(_favouriteListCoins.value.orEmpty().sortedBy { it.coinName })
    }

    fun sortedByZA() {
        _favouriteListCoins.postValue(_favouriteListCoins.value.orEmpty().sortedBy { it.coinName }
            .reversed())
    }

    fun sortedByHighValue() {
        _favouriteListCoins.postValue(
            _favouriteListCoins.value.orEmpty().sortedByDescending { it.coinPrice })
    }

    fun sortedByLowValue() {
        _favouriteListCoins.postValue(_favouriteListCoins.value.orEmpty().sortedBy { it.coinPrice })
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
