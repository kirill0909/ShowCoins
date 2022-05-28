package com.example.showcoins.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.showcoins.R
import com.example.showcoins.databinding.CoinsItemBinding
import com.example.showcoins.util.CoinsDiffUtil

class CoinsListAdapter : RecyclerView.Adapter<CoinsListAdapter.CoinsListViewHolder>() {

    private var fakerList = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoinsListViewHolder {
        return CoinsListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.coins_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: CoinsListViewHolder, position: Int) {
        val coin = fakerList[position]

        holder.tvCoin.text = coin
    }

    override fun getItemCount(): Int {
        return fakerList.size
    }

    fun setData(newCoinsList: MutableList<String>) {
        val diffUtil = CoinsDiffUtil(fakerList, newCoinsList)
        val diffResults = DiffUtil.calculateDiff(diffUtil)
        fakerList = newCoinsList
        diffResults.dispatchUpdatesTo(this)
    }

    inner class CoinsListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = CoinsItemBinding.bind(itemView)

        val tvCoin = binding.tvCoin
        val ivStar = binding.ivStar
    }
}