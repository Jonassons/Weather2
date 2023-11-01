package com.example.weather

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.converter.gson.GsonConverterFactory

// Define the data class for the weather response
data class WeatherResponse(val main: WeatherMain)

data class WeatherMain(val temp: Double, val pressure: Int, val humidity: Int)

// Define the API interface
interface WeatherApi {
    @GET("weather")
    fun getCurrentWeather(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String
    ): Call<WeatherResponse>
}

// Create a Singleton object for Retrofit
object WeatherService {
    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val weatherApi: WeatherApi = retrofit.create(WeatherApi::class.java)
}
