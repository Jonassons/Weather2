package com.example.weather


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StockholmFragment : Fragment() {

    private val apiKey = "24ac78ceb6c71b7b2443e257e39fbb7e"
    private val cityName = "Stockholm"

    private lateinit var temperatureTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the fragment's layout
        val view = inflater.inflate(R.layout.stockholm, container, false)

        // Find views within the inflated layout
        temperatureTextView = view.findViewById(R.id.temperatureTextView)

        // Make the API call
        WeatherService.weatherApi.getCurrentWeather(cityName, apiKey)
            .enqueue(object : Callback<WeatherResponse> {
                override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                    if (response.isSuccessful) {
                        val weatherResponse = response.body()
                        val temperature = weatherResponse?.main?.temp
                        temperatureTextView.text = "Temperature in Stockholm: ${temperature}°K"
                    } else {
                        // Handle API error
                    }
                }

                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    // Handle network error
                }

            })


        return view
    }
}
