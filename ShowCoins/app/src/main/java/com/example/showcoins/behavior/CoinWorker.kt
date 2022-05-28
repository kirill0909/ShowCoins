package com.example.showcoins.behavior

import androidx.fragment.app.Fragment
import com.example.showcoins.behavior.interfaces.SortingByAZBehavior
import com.example.showcoins.model.Coin

open class CoinWorker : Fragment() {

    open lateinit var sortingByAZBehavior: SortingByAZBehavior

    fun performSortingByAZ(listCoins: List<Coin>): List<Coin> {
        return sortingByAZBehavior.sortingByAZ(listCoins)
    }
}