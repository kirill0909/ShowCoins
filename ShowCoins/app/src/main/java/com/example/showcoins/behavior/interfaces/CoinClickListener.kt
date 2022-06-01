package com.example.showcoins.behavior.interfaces

import com.example.showcoins.model.Coin
import android.view.View

interface CoinClickListener {
    fun onMoreButtonClick(coin: Coin, view: View)
}