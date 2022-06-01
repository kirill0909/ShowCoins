package com.example.showcoins.behavior.classes

import com.example.showcoins.behavior.interfaces.RemoveCoinFromFavouritesBehavior
import com.example.showcoins.model.FavouriteCoin
import android.util.Log
import android.view.View
import com.example.showcoins.viewmodel.FavouriteCoinViewModel
import com.google.android.material.snackbar.Snackbar

class RemoveCoinFromFavourites : RemoveCoinFromFavouritesBehavior {

    override fun removeCoinFromFavourites(
        favouriteCoin: FavouriteCoin,
        view: View,
        favouriteCoinViewModel: FavouriteCoinViewModel
    ) {
        favouriteCoinViewModel.remove(favouriteCoin)
        showSnackBar(view, "Coin: ${favouriteCoin.coinName} was removed from favourites")
    }

    /*
    *This method show simple snackBar notification
     */
    private fun showSnackBar(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
    }
}