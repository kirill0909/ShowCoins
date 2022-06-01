package com.example.showcoins.util

import androidx.recyclerview.widget.DiffUtil
import com.example.showcoins.model.Coin
import com.example.showcoins.model.FavouriteCoin

class FavouriteCoinsDiffUtil(
    private val oldList: List<FavouriteCoin>,
    private val newList: List<FavouriteCoin>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].coinName == newList[newItemPosition].coinName
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition].coinName != newList[newItemPosition].coinName -> {
                false
            }
            oldList[oldItemPosition].coinPrice != newList[newItemPosition].coinPrice -> {
                false
            }
            else -> true
        }
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}