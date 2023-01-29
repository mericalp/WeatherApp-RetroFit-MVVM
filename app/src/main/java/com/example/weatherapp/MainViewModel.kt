package com.example.weatherapp

import Root
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExecutorCoroutineDispatcher
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val repository: Repository,
) : ViewModel() {

    // Bunu yazmamdaki sebep şu : View’ın LiveData’ya erişip onun ile
    // ilgili bir işlem yaparsa kendi state’ini değiştirmiş olacak.
    // Ancak bu viewModel’ın sorumluluğunda olan bir durum,
    // view sadece bu LiveData’yı observe etmeli diye ,
    // bu sebeple LiveData’ya encapsulation uygulamamız gerekiyor.

    // The reason I'm writing this is: If the View accesses LiveData and does something about it,
    // it will change its own state. However, this is the responsibility of the viewModel,
    // the view should only observe this LiveData,
    // so we need to encapsulate the LiveData.

    private var _myWeatherResponse = MutableLiveData<Response<Root>>()
    val myWeatherResponse: LiveData<Response<Root>> = _myWeatherResponse

    private val _myWeatherWeekResponse = MutableLiveData<Root>()
    val myWeatherWeekResponse: LiveData<Root> = _myWeatherWeekResponse


    fun getData() {
        viewModelScope.launch {
            try {
                val response = repository.getWeatherData()
                _myWeatherResponse.value = response
            } catch (e: Exception) {
                Log.d("Error", e.message.toString())
            }
        }
    }

    fun getDataWeek() {
        viewModelScope.launch {
            try {
                val response = repository.getWeatherWeekData()
                _myWeatherWeekResponse.value = response
            } catch (e: Exception) {
                Log.d("Error", e.message.toString())
            }
        }
    }
}