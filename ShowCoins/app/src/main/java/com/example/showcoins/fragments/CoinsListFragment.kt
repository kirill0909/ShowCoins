package com.example.showcoins.fragments

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.showcoins.R
import com.example.showcoins.adapters.CoinsListAdapter
import com.example.showcoins.databinding.FragmentCoinsListBinding
import com.example.showcoins.repository.Repository
import com.example.showcoins.viewmodel.CoinsViewModel
import com.example.showcoins.viewmodel.CoinsViewModelFactory
import android.util.Log
import android.view.*
import com.example.showcoins.behavior.CoinWorker
import com.example.showcoins.behavior.classes.SortingByAZ
import com.example.showcoins.behavior.interfaces.SortingByAZBehavior

class CoinsListFragment : CoinWorker() {

    private lateinit var binding: FragmentCoinsListBinding
    private val adapter by lazy { CoinsListAdapter(requireContext()) }
    private lateinit var coinsViewModel: CoinsViewModel
    override var sortingByAZBehavior: SortingByAZBehavior = SortingByAZ()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_coins_list, container, false)
        binding = FragmentCoinsListBinding.bind(view)

        //create view model
        val coinsViewModelFactory = CoinsViewModelFactory(Repository())
        coinsViewModel = ViewModelProvider(this, coinsViewModelFactory)[CoinsViewModel::class.java]

        //set title to the toolbar
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarMain.root)
        (activity as AppCompatActivity).supportActionBar?.title = coinsViewModel.title

        //set adapter and layout manager
        binding.recyclerViewCoins.adapter = adapter
        binding.recyclerViewCoins.layoutManager = LinearLayoutManager(requireContext())

        //call method for get coins and observe for it
        coinsViewModel.listCoins.observe(viewLifecycleOwner) { coins ->
            if (coins.isNotEmpty()) {
                adapter.setData(coins)
            } else {
                Log.d("CoinsListFragment", "Error")
            }
        }


        setHasOptionsMenu(true)
        return view
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
            R.id.sorting_by_az -> coinsViewModel.sortedByAz(binding)
            R.id.sorting_by_za -> coinsViewModel.sortedByZa(binding)
            R.id.sorting_by_high_value -> coinsViewModel.sortedByHighValue(binding)
            R.id.sorting_by_low_value -> coinsViewModel.sortedByLowValue(binding)
        }
        return super.onOptionsItemSelected(item)
    }
}