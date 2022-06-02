package com.example.showcoins.fragments

import android.widget.Toast
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.*
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.showcoins.R
import com.example.showcoins.databinding.FragmentFavouritesCoinsBinding
import com.example.showcoins.viewmodel.FavouriteCoinViewModel
import com.example.showcoins.adapters.FavouriteCoinsListAdapter
import com.example.showcoins.behavior.CoinWorker
import com.example.showcoins.behavior.classes.RemoveCoinFromFavourites
import com.example.showcoins.behavior.interfaces.FavouriteCoinClickListener
import com.example.showcoins.behavior.interfaces.RemoveCoinFromFavouritesBehavior
import com.example.showcoins.model.FavouriteCoin

class FavouritesCoinsFragment : CoinWorker(), FavouriteCoinClickListener {

    private lateinit var binding: FragmentFavouritesCoinsBinding
    lateinit var favouriteCoinViewModel: FavouriteCoinViewModel
    private val adapter by lazy { FavouriteCoinsListAdapter(requireContext(), this) }
    override var removeCoinFromFavouritesBehavior: RemoveCoinFromFavouritesBehavior =
        RemoveCoinFromFavourites()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_favourites_coins, container, false)
        binding = FragmentFavouritesCoinsBinding.bind(view)

        favouriteCoinViewModel = ViewModelProvider(this)[FavouriteCoinViewModel::class.java]

        binding.recyclerViewFavouritesCoins.adapter = adapter
        binding.recyclerViewFavouritesCoins.layoutManager = LinearLayoutManager(requireContext())

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarMain.root)
        (activity as AppCompatActivity).supportActionBar?.title = favouriteCoinViewModel.title

        favouriteCoinViewModel.favouriteListCoins.observe(viewLifecycleOwner) { favouriteCoin ->
            adapter.setData(favouriteCoin)
        }

		setHasOptionsMenu(true)
        return view
    }

    @SuppressLint("DiscouragedPrivateApi")
    override fun onFavouriteMoreButtonClick(favouriteCoin: FavouriteCoin, view: View) {
        val popupMenu = PopupMenu(requireContext(), view, Gravity.END)
        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.context_remove_from_favourite -> {
                    performRemoveCoinFromFavourites(favouriteCoin, view, favouriteCoinViewModel)
                    true
                }
                else -> false
            }

        }
        popupMenu.inflate(R.menu.more_button_favourite_popup_menu)
        try {
            val fieldMPopup = PopupMenu::class.java.getDeclaredField("mPopup")
            fieldMPopup.isAccessible = true
            val mPopup = fieldMPopup.get(popupMenu)
            mPopup.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                .invoke(mPopup, true)
        } catch (e: Exception) {
            Log.d("FavouriteCoinsFragment", "Error showing menu icons", e)
        } finally {
            popupMenu.show()
        }
    }

    /*
            *This method create and options menu to the toolbar
             */
        override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
            inflater.inflate(R.menu.sorting_menu, menu)
            super.onCreateOptionsMenu(menu, inflater)
        }
    
        /*
        *This method processes tap on the popup menu item
         */
        override fun onOptionsItemSelected(item: MenuItem): Boolean {
            when (item.itemId) {
                R.id.sorting_by_az -> favouriteCoinViewModel.sortedByAZ()
                R.id.sorting_by_za -> favouriteCoinViewModel.sortedByZA()
                R.id.sorting_by_high_value ->favouriteCoinViewModel.sortedByHighValue()
                R.id.sorting_by_low_value -> favouriteCoinViewModel.sortedByLowValue()
            }
            return super.onOptionsItemSelected(item)
        }
    
        /*
        *This message show simple notification
         */
        private fun toast(message: String) {
            Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
        }
}
