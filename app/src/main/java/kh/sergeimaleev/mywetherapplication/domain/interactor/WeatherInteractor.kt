package kh.sergeimaleev.mywetherapplication.domain.interactor

import androidx.lifecycle.LiveData
import kh.sergeimaleev.mywetherapplication.data.local.db.entities.WeatherForecastEntity
import kh.sergeimaleev.mywetherapplication.data.rest.model.WeatherCurrentResponse
import kh.sergeimaleev.mywetherapplication.data.rest.model.forecastmodel.WeatherForecastResponse

interface WeatherInteractor {

    val weatherForecastDatabaseLiveData: LiveData<List<WeatherForecastEntity>>
    val weatherLastForecastDatabaseLiveData: LiveData<WeatherForecastEntity>

    fun getWeatherCurrentByCoordinates(
        lat: Double,
        lon: Double,
        onSuccess: (WeatherCurrentResponse) -> Unit,
        onFail: (error: Throwable) -> Unit
    )

    fun getWeatherForecastByCoordinates(
        lat: Double,
        lon: Double,
        onSuccess: (WeatherForecastResponse) -> Unit,
        onFail: (error: Throwable) -> Unit
    )

    fun getWeatherForecastFromDatabase(
        onSuccess: (List<WeatherForecastEntity>) -> Unit,
        onFail: (error: Throwable) -> Unit
    )

    fun setWeatherForecastToDatabase(
        readyResult: WeatherForecastResponse,
        onFail: (error: Throwable) -> Unit
    )
}