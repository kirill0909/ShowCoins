package com.example.showcoins.behavior.classes

import com.example.showcoins.behavior.interfaces.AddCoinToFavouriteBehavior
import com.example.showcoins.model.Coin
import com.example.showcoins.model.FavouriteCoin
import com.example.showcoins.viewmodel.FavouriteCoinViewModel
import android.view.View
import com.google.android.material.snackbar.Snackbar

class AddCoinToFavourite : AddCoinToFavouriteBehavior {

    /*
    *This method add favourite coins to db
     */
    override fun addCoinToFavourite(
        favouriteCoinViewModel: FavouriteCoinViewModel,
        coin: Coin,
        view: View
    ) {
        val favouriteCoin = FavouriteCoin(0, coin.name, coin.price_usd, true)
        favouriteCoinViewModel.addCoinToFavourite(favouriteCoin)
        showSnackBar(view, "Coin: ${favouriteCoin.coinName} was add to favourite")
    }

    /*
    *This method show simple snackBar notification
     */
    private fun showSnackBar(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
    }

}