package com.example.showcoins.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.showcoins.R
import com.example.showcoins.adapters.CoinsListAdapter
import com.example.showcoins.databinding.FragmentCoinsListBinding
import com.github.javafaker.Faker

class CoinsListFragment : Fragment() {

    private lateinit var binding: FragmentCoinsListBinding
    private val adapter by lazy { CoinsListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_coins_list, container, false)
        binding = FragmentCoinsListBinding.bind(view)

        //set title to the toolbar
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbarMain.root)
        (activity as AppCompatActivity).supportActionBar?.title =
            "General list of coins"//mCategoryViewModel.title

        //set adapter and layout manager
        binding.recyclerViewCoins.adapter = adapter
        binding.recyclerViewCoins.layoutManager = LinearLayoutManager(requireContext())

        //there should be observer
        adapter.setData(getFakerList(20))

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