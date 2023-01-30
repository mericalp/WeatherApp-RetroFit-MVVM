package com.example.weatherapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.weatherapp.api.ApiService.Companion.city
import com.example.weatherapp.adapter.WeekAdapter
import com.example.weatherapp.databinding.FragmentWeekBinding


class WeekFragment : Fragment() {

    private val mainViewModel: MainViewModel by activityViewModels()

    private lateinit var binding: FragmentWeekBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment -- Bu parca icin duzeni saglar.
        binding = FragmentWeekBinding.inflate(layoutInflater, container, false)
        
        var cityWeek = city

        mainViewModel.getDataWeek()

        // Configuring the adapter - Adapteri yapilandirdik...
        val mAdapter = WeekAdapter()
        binding.rvlWeek.layoutManager = LinearLayoutManager(context)
        binding.rvlWeek.adapter = mAdapter
        binding.rvlWeek.setHasFixedSize(true)

        // View Model icerisindeki live datami observe ediyorum ve bir degisiklik oldugunda view bundan haberdar olsun diye
        // I observe the live data in the view model and when there is a change, so that the view is aware of it.
        mainViewModel.myWeatherWeekResponse.observe(viewLifecycleOwner) { response ->
            if (response != null) {
                mAdapter.setDataWeek(response.list)
            }
        }

        // Navigate: weekFragment_to_homeFragment
        binding.imgBack.setOnClickListener {
            findNavController().navigate(R.id.action_weekFragment_to_homeFragment)
        }

        return binding.root
    }


}