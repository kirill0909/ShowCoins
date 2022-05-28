package com.example.showcoins.util

import androidx.recyclerview.widget.DiffUtil

class CoinsDiffUtil(
    private val oldList: List<String>,
    private val newList: List<String>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldList[oldItemPosition] != newList[newItemPosition] -> {
                false
            }
            oldList[oldItemPosition] != newList[newItemPosition] -> {
                false
            }
            else -> true
        }
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        return super.getChangePayload(oldItemPosition, newItemPosition)
    }
}