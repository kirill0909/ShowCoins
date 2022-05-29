package com.example.showcoins.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.showcoins.model.Coin
import com.example.showcoins.repository.Repository
import kotlinx.coroutines.launch
import com.example.showcoins.databinding.FragmentCoinsListBinding

class CoinsViewModel(private val repository: Repository) : ViewModel() {

    private var _listCoins: MutableLiveData<List<Coin>> = MutableLiveData()
    val listCoins: LiveData<List<Coin>>
            get() = _listCoins

    val title = "General list of coins"

    init {
        getCoins()
    }

    private fun getCoins() {
        viewModelScope.launch {
            _listCoins.value = repository.getCoins()
        }
    }

    /*
    *This method sort coins list and scroll to the top list
     */
    fun sortedByAz(binding: FragmentCoinsListBinding) {
        _listCoins.postValue(_listCoins.value?.sortedBy { it.name })
        binding.recyclerViewCoins.layoutManager?.scrollToPosition(0)
    }
}