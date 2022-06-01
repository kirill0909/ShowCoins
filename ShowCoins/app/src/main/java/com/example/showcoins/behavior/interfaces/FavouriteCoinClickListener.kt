package com.example.showcoins.behavior.interfaces

import com.example.showcoins.model.FavouriteCoin
import android.view.View

interface FavouriteCoinClickListener {

    fun onFavouriteMoreButtonClick(favouriteCoin: FavouriteCoin, view: View)
}