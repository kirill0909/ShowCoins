package com.example.showcoins.behavior.classes

import com.example.showcoins.behavior.interfaces.SortingByAZBehavior
import com.example.showcoins.model.Coin

class SortingByAZ : SortingByAZBehavior {

    /*
    *This method sort list of coins and return it
     */
    override fun sortingByAZ(listCoins: List<Coin>): List<Coin> {
        val newList = mutableListOf<Coin>()
        listCoins.forEach {
            if (it.name.first().isLetter()) {
                newList.add(it)
            }
        }
        return newList.sortedBy { it.name }
    }
}