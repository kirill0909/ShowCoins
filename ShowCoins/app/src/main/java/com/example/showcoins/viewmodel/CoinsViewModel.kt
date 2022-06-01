package com.example.showcoins.viewmodel

import com.example.showcoins.model.Coin
import com.example.showcoins.repository.Repository
import kotlinx.coroutines.launch
import com.example.showcoins.databinding.FragmentCoinsListBinding
import androidx.lifecycle.*

class CoinsViewModel(
    private val repository: Repository) : ViewModel() {

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
    *This method sort coins list by A-Z and scroll to the top list
     */
    fun sortedByAz(binding: FragmentCoinsListBinding) {
        _listCoins.postValue(_listCoins.value?.sortedBy { it.name })
        binding.recyclerViewCoins.layoutManager?.scrollToPosition(0)

    }

    /*
    *This method sort coins list by Z-A and scroll to the top list
     */
    fun sortedByZa(binding: FragmentCoinsListBinding) {
        _listCoins.postValue(_listCoins.value?.sortedBy { it.name }?.reversed())
        binding.recyclerViewCoins.layoutManager?.scrollToPosition(0)

    }

    /*
    *This method sort coins list by high value and scroll to the top list
     */
    fun sortedByHighValue(binding: FragmentCoinsListBinding) {
        _listCoins.postValue(_listCoins.value?.sortedByDescending { it.price_usd.toDouble() })
        binding.recyclerViewCoins.layoutManager?.scrollToPosition(0)
    }

    /*
    *This method sort coins list by low value and scroll to the top list
     */
    fun sortedByLowValue(binding: FragmentCoinsListBinding) {
        _listCoins.postValue(_listCoins.value?.sortedBy { it.price_usd.toDouble() })
        binding.recyclerViewCoins.layoutManager?.scrollToPosition(0)

    }

}