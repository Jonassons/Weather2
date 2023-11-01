package com.example.weather
import com.example.weather.StockholmFragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val apiKey = "24ac78ceb6c71b7b2443e257e39fbb7e"
    private val cityName = "Malmo"

    private lateinit var temperatureTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        temperatureTextView = findViewById(R.id.temperatureTextView)

        // Make the API call
        WeatherService.weatherApi.getCurrentWeather(cityName, apiKey)
            .enqueue(object : Callback<WeatherResponse> {
                override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
                    if (response.isSuccessful) {
                        val weatherResponse = response.body()
                        val temperature = weatherResponse?.main?.temp
                        temperatureTextView.text = "Temperature in Malmö : ${temperature}°K"
                    } else {
                        // Handle API error
                    }
                }

                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    // Handle network error
                }
            })
        val stockholmButton = findViewById<Button>(R.id.buttonStockholm)

        stockholmButton.setOnClickListener {
            // Replace the current fragment with StockholmFragment
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container, StockholmFragment())
            transaction.addToBackStack(null) // Optional: Add the transaction to the back stack
            transaction.commit()

            val parisButton = findViewById<Button>(R.id.buttonParis)

            parisButton.setOnClickListener {
                // Replace the current fragment with ParisFragment
                val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_container, ParisFragment())
                transaction.addToBackStack(null) // Optional: Add the transaction to the back stack
                transaction.commit()
            }

            val copenhagenButton = findViewById<Button>(R.id.buttonCopenhagen)

            copenhagenButton.setOnClickListener {
                // Replace the current fragment with CopenhagenFragment
                val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragment_container, CopenhagenFragment())
                transaction.addToBackStack(null) // Optional: Add the transaction to the back stack
                transaction.commit()
            }
    }



}}
