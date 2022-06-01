package com.example.showcoins.behavior

import androidx.fragment.app.Fragment
import com.example.showcoins.behavior.interfaces.AddCoinToFavouriteBehavior
import com.example.showcoins.viewmodel.FavouriteCoinViewModel
import com.example.showcoins.model.Coin
import com.example.showcoins.model.FavouriteCoin
import android.view.View
import com.example.showcoins.behavior.interfaces.RemoveCoinFromFavouritesBehavior

open class CoinWorker : Fragment() {

    open lateinit var addCoinToFavouriteBehavior: AddCoinToFavouriteBehavior
    open lateinit var removeCoinFromFavouritesBehavior: RemoveCoinFromFavouritesBehavior

    fun performAddCoinToFavourite(
        favouriteCoinViewModel: FavouriteCoinViewModel,
        coin: Coin,
        view: View
    ) {
        addCoinToFavouriteBehavior.addCoinToFavourite(favouriteCoinViewModel, coin, view)
    }

    fun performRemoveCoinFromFavourites(
        favouriteCoin: FavouriteCoin,
        view: View,
        favouriteCoinViewModel: FavouriteCoinViewModel
    ) {
        removeCoinFromFavouritesBehavior.removeCoinFromFavourites(
            favouriteCoin,
            view,
            favouriteCoinViewModel
        )
    }
}