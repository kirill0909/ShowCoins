package com.example.showcoins.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.showcoins.R
import com.example.showcoins.behavior.interfaces.FavouriteCoinClickListener
import com.example.showcoins.databinding.CoinsItemBinding
import com.example.showcoins.model.FavouriteCoin
import com.example.showcoins.util.FavouriteCoinsDiffUtil

class FavouriteCoinsListAdapter(
    private val context: Context,
    private val listener: FavouriteCoinClickListener
) :
    RecyclerView.Adapter<FavouriteCoinsListAdapter.FavouriteCoinsListViewHolder>() {

    private var favouriteCoinsList = listOf<FavouriteCoin>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FavouriteCoinsListViewHolder {
        return FavouriteCoinsListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.coins_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: FavouriteCoinsListViewHolder, position: Int) {
        val favouriteCoin = favouriteCoinsList[position]

        holder.tvCoin.text =
            context.getString(
                R.string.item_coin,
                favouriteCoin.coinName,
                favouriteCoin.coinPrice.toString().substring(0, 7)
            )

        holder.ivMoreButton.setOnClickListener {
            listener.onFavouriteMoreButtonClick(favouriteCoin, holder.itemView)
        }
    }

    override fun getItemCount(): Int {
        return favouriteCoinsList.size
    }

    fun setData(newFavouriteCoinsList: List<FavouriteCoin>) {
        val diffUtil = FavouriteCoinsDiffUtil(favouriteCoinsList, newFavouriteCoinsList)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        favouriteCoinsList = newFavouriteCoinsList
        diffResults.dispatchUpdatesTo(this)
    }

    inner class FavouriteCoinsListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = CoinsItemBinding.bind(itemView)

        val tvCoin = binding.tvCoin
        val ivMoreButton = binding.ivMoreButton
    }
}