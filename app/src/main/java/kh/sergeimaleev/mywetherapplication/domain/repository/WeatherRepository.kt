package kh.sergeimaleev.mywetherapplication.domain.repository

import androidx.lifecycle.LiveData
import kh.sergeimaleev.mywetherapplication.data.local.db.entities.WeatherForecastEntity
import kh.sergeimaleev.mywetherapplication.data.rest.model.WeatherCurrentResponse
import kh.sergeimaleev.mywetherapplication.data.rest.model.forecastmodel.WeatherForecastResponse
import kotlinx.coroutines.Deferred

interface WeatherRepository {

    suspend fun getWeatherCurrentByCoordinatesAsync(
        lat: Double,
        lon: Double
    ): Deferred<WeatherCurrentResponse>

    suspend fun getWeatherForecastByCoordinatesAsync(
        lat: Double,
        lon: Double
    ): Deferred<WeatherForecastResponse>

    suspend fun setWeatherForecastDB(readyResult: WeatherForecastResponse)
    suspend fun getWeatherForecastDBList(): List<WeatherForecastEntity>
    fun getWeatherForecastDBLiveData(): LiveData<List<WeatherForecastEntity>>
    fun getWeatherForecastLastDBLiveData(): LiveData<WeatherForecastEntity>

}