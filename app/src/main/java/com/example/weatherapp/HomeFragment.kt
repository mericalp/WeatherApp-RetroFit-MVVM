package com.example.weatherapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.adapter.HomeAdapter
import com.example.weatherapp.api.ApiService.Companion.city
import com.example.weatherapp.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment -- Bu parca icin duzeni saglar.
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

        mainViewModel.getData()

        // Configuring the adapter - Adapteri yapilandirdik...
        val mAdapter = HomeAdapter()
        binding.rvlHomeWeather.layoutManager = LinearLayoutManager(context)
        binding.rvlHomeWeather.adapter = mAdapter
        binding.rvlHomeWeather.setHasFixedSize(true)
        binding.btSearch.setOnClickListener {
            try {
                val text = binding.edtCidade.text.toString()
                city = text

                mainViewModel.getData()
            }catch (e: Exception){
                Log.d("unexpected error", e.message.toString())
            }
        }

        // View Model icerisindeki live datami observe ediyorum ve bir degisiklik oldugunda view bundan haberdar olsun diye
        // I observe the live data in the view model and when there is a change, so that the view is aware of it.
        mainViewModel.myWeatherResponse.observe(viewLifecycleOwner) { response ->
            Log.d("Request", response.body().toString())
            if (response != null){
                mAdapter.setData(response.body()!!)
            }
        }

        // Navigate: homeFragment_to_weekFragment
        binding.imgNextWeek.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_weekFragment)
        }

        return binding.root
    }
}
