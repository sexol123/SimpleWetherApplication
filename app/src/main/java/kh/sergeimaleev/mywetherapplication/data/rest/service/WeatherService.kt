package kh.sergeimaleev.mywetherapplication.data.rest.service

import kh.sergeimaleev.mywetherapplication.data.rest.model.WeatherCurrentResponse
import kh.sergeimaleev.mywetherapplication.data.rest.model.WeatherUnits
import kh.sergeimaleev.mywetherapplication.data.rest.model.forecastmodel.WeatherForecastResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("/data/2.5/weather")
    fun getWeatherCurrentByCoordinatesAsync(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: WeatherUnits = WeatherUnits.METRIC
    ): Deferred<WeatherCurrentResponse>

    @GET("/data/2.5/forecast")
    fun getWeatherForecastByCoordinatesAsync(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: WeatherUnits = WeatherUnits.METRIC
    ): Deferred<WeatherForecastResponse>
}