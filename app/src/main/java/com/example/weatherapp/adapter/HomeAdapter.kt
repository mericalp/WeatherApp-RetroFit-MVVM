package com.example.weatherapp.adapter

import Root
import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.CardLayoutBinding
import java.text.SimpleDateFormat
import java.util.*

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private var listWeather = emptyList<Root>()

    class HomeViewHolder(val binding: CardLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(CardLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    // RecyclerView'i CardLayout'a bagladik ve listWeather uzerinden datalari bagladik.
    // We connected the card layout via Holder and connected the data via listWeather.
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        // For our cards on the forecast screen for the current
        val data = listWeather[position]

        holder.binding.address.text = data.city.name
        holder.binding.txtTemp.text = data.list[0].main.temp.toInt().toString() + "ºC"
        holder.binding.txtMaxTemp.text =
            "temp max: " + data.list.get(0).main.tempMax.toInt().toString() + "ºC"
        holder.binding.txtMinTemp.text =
            "temp min: " + data.list.get(0).main.tempMin.toInt().toString() + "ºC"
        holder.binding.humidity.text = data.list.get(0).main.humidity.toString()
        holder.binding.sunrise.text =
            SimpleDateFormat("hh:mm a", Locale.getDefault())
                .format(
                    Date(data.city.sunrise * 1000))
        holder.binding.sunset.text =
            SimpleDateFormat("hh:mm a", Locale.getDefault())
                .format(
                    Date(data.city.sunset * 1000))

        // Root|list -> imageStatusleri duzenledik.
        // Root|list -> We edited the imageStatus from the list.
        when (data.list[position].weather[0].main) {
            "Thunderstorm" -> {
                holder.binding.imageStatusHome.setImageResource(R.drawable.chuva)
            }
            "Rain" -> {
                holder.binding.imageStatusHome.setImageResource(R.drawable.chuvaleve)
            }
            "Clear" -> {
                holder.binding.imageStatusHome.setImageResource(R.drawable.sun)
            }
            "Clouds" -> {
                holder.binding.imageStatusHome.setImageResource(R.drawable.cloudy)
            }
            else -> {
                holder.binding.imageStatusHome.setImageResource(R.drawable.cloudy)
            }
        }
    }

    // Item Count -> List
    override fun getItemCount(): Int {
        return listWeather.size
    }

    // HomeFragment will use setData if response is not empty
    fun setData(list: Root) {
        listWeather = listOf(list)
        notifyDataSetChanged()
    }

}