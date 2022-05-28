package com.example.showcoins.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.showcoins.model.Coin
import com.example.showcoins.repository.Repository
import kotlinx.coroutines.launch

class CoinsViewModel(private val repository: Repository) : ViewModel() {

    var listCoins: MutableLiveData<List<Coin>> = MutableLiveData()
    val title = "General list of coins"

    fun getCoins() {
        viewModelScope.launch {
            listCoins.value = repository.getCoins()
        }
    }
}