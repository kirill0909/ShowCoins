package com.example.showcoins.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.showcoins.R
import com.example.showcoins.databinding.FragmentFavouritesCoinsBinding

class FavouritesCoinsFragment : Fragment() {

    private lateinit var binding: FragmentFavouritesCoinsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favourites_coins, container, false)
        binding = FragmentFavouritesCoinsBinding.bind(view)

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarMain.root)
        (activity as AppCompatActivity).supportActionBar?.title =
            "Favourites coins"//mCategoryViewModel.title

        return view
    }
}