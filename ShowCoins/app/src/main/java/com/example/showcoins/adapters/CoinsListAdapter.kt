package com.example.showcoins.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.showcoins.R
import com.example.showcoins.behavior.interfaces.CoinClickListener
import com.example.showcoins.databinding.CoinsItemBinding
import com.example.showcoins.model.Coin
import com.example.showcoins.util.CoinsDiffUtil

class CoinsListAdapter(private val context: Context, private val listener: CoinClickListener) :
    RecyclerView.Adapter<CoinsListAdapter.CoinsListViewHolder>() {

    private var coinsList = listOf<Coin>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinsListViewHolder {
        return CoinsListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.coins_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CoinsListViewHolder, position: Int) {
        val coin = coinsList[position]

        holder.tvCoin.text =
            context.getString(R.string.item_coin, coin.name, coin.price_usd.substring(0, 7))

        holder.ivMoreButton.setOnClickListener {
            listener.onMoreButtonClick(coin, holder.itemView)
        }
    }

    override fun getItemCount(): Int {
        return coinsList.size
    }

    fun setData(newCoinsList: List<Coin>) {
        val diffUtil = CoinsDiffUtil(coinsList, newCoinsList)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        coinsList = newCoinsList
        diffResults.dispatchUpdatesTo(this)
    }

    inner class CoinsListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = CoinsItemBinding.bind(itemView)

        val tvCoin = binding.tvCoin
        val ivMoreButton = binding.ivMoreButton
    }
}