package com.example.showcoins.behavior.interfaces

import com.example.showcoins.model.Coin
import com.example.showcoins.viewmodel.FavouriteCoinViewModel
import android.view.View
import android.widget.PopupMenu

interface AddCoinToFavouriteBehavior {

    fun addCoinToFavourite(favouriteCoinViewModel: FavouriteCoinViewModel, coin: Coin, view: View)
}