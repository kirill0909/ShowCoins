package com.example.showcoins.behavior.interfaces

import com.example.showcoins.model.FavouriteCoin
import com.example.showcoins.viewmodel.FavouriteCoinViewModel
import android.view.View

interface RemoveCoinFromFavouritesBehavior {

    fun removeCoinFromFavourites(favouriteCoin: FavouriteCoin, view: View, favouriteCoinViewModel: FavouriteCoinViewModel)
}