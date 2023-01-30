package com.example.weatherapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.CardWeekLayoutBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt
import kotlin.time.Duration.Companion.days

class WeekAdapter : RecyclerView.Adapter<WeekAdapter.WeekViewHolder>() {

    private var listWeatherWeek = emptyList<com.example.weatherapp.Model.List>()

    class WeekViewHolder(val binding: CardWeekLayoutBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekViewHolder {
        return WeekViewHolder(
            CardWeekLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    // RecyclerView'i CardWeekLayout bagladik ve listWeatherWeek uzerinden datalari bagladik.
    // We connected the CardWeekLayout via Holder and connected the data via listWeatherWeek.
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: WeekViewHolder, position: Int) {
        // For our cards on the forecast screen for the week
        val data = listWeatherWeek[position]

        // here is a temperature text
        // holder.binding.txtTemp.text = data.main.temp.toInt().toString() + "ºC"
        holder.binding.txtMaxTemp.text =
            "" + data.main.tempMax.toInt().toString() + "ºC"
        holder.binding.txtMinTemp.text =
            "" + data.main.tempMin.toInt().toString() + "ºC"
        holder.binding.txtHour.text =
            SimpleDateFormat("hh:mm a", Locale.getDefault())
                .format(
                    Date(
                        data.dt * 1000))

        val d = Date(data.dt * 1000)

        // Set day
        when (d.day) {
            1 -> holder.binding.txtDia.text = "Mon"

            2 -> holder.binding.txtDia.text = "Tue"

            3 -> holder.binding.txtDia.text = "Wed"

            4 -> holder.binding.txtDia.text = "Thu"

            5 -> holder.binding.txtDia.text = "Fri"

            6 -> holder.binding.txtDia.text = "Sat"

            0 -> holder.binding.txtDia.text = "Sun"
        }

        // Root|list ->  imageStatusleri duzenledik.
        // Root|list -> We edited the imageStatus from the list.
        when (data.weather[0].main) {
            "Thunderstorm" -> {
                holder.binding.imageStatus.setImageResource(R.drawable.chuva)
            }
            "Rain" -> {
                holder.binding.imageStatus.setImageResource(R.drawable.chuvaleve)
            }
            "Clear" -> {
                holder.binding.imageStatus.setImageResource(R.drawable.sun)
            }
            "Clouds" -> {
                holder.binding.imageStatus.setImageResource(R.drawable.cloudy)
            }
            else -> {
                holder.binding.imageStatus.setImageResource(R.drawable.cloudy)
            }
        }

    }

    // Item Count -> Root
    override fun getItemCount(): Int {
        return listWeatherWeek.size
    }

    // WeekFragment will use setDataWeek if response is not empty
    fun setDataWeek(list: List<com.example.weatherapp.Model.List>) {
        listWeatherWeek = list
        notifyDataSetChanged()
    }
}