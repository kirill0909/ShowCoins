package com.example.showcoins.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.showcoins.R
import com.example.showcoins.adapters.CoinsListAdapter
import com.example.showcoins.databinding.FragmentCoinsListBinding
import com.example.showcoins.repository.Repository
import com.example.showcoins.viewmodel.CoinsViewModel
import com.example.showcoins.viewmodel.CoinsViewModelFactory
import com.github.javafaker.Faker
import android.util.Log

class CoinsListFragment : Fragment() {

    private lateinit var binding: FragmentCoinsListBinding
    private val adapter by lazy { CoinsListAdapter() }
    private lateinit var coinsViewModel: CoinsViewModel
    private val TAG = "CoinsListFragment"

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
        coinsViewModel.getCoins()
        coinsViewModel.listCoins.observe(viewLifecycleOwner) { response ->
            if(response.isSuccessful) {
                adapter.setData(response.body()!!)
            }else {
                Log.d(TAG, "${response.code()}")
            }
        }
        return view
    }

    /*
    *This method return faker list
     */
    private fun getFakerList(num: Int): MutableList<String> {
        val faker = Faker.instance()
        val fakerList = mutableListOf<String>()
        for(i in 0..num) {
            fakerList.add(faker.name().name())
        }
        return fakerList
    }

}