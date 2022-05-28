package com.example.showcoins.behavior.interfaces

import com.example.showcoins.model.Coin

interface SortingByAZBehavior {

    fun sortingByAZ(listCoins: List<Coin>): List<Coin>
}