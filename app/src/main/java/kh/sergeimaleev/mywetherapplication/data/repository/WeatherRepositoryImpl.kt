package kh.sergeimaleev.mywetherapplication.data.repository

import androidx.lifecycle.LiveData
import kh.sergeimaleev.mywetherapplication.data.local.db.dao.WeatherForecastDAO
import kh.sergeimaleev.mywetherapplication.data.local.db.entities.WeatherForecastEntity
import kh.sergeimaleev.mywetherapplication.data.rest.model.WeatherCurrentResponse
import kh.sergeimaleev.mywetherapplication.data.rest.model.forecastmodel.WeatherForecastResponse
import kh.sergeimaleev.mywetherapplication.data.rest.service.WeatherService
import kh.sergeimaleev.mywetherapplication.domain.repository.WeatherRepository
import kotlinx.coroutines.Deferred

class WeatherRepositoryImpl(
    private val weatherService: WeatherService,
    private val weatherForecastDAO: WeatherForecastDAO
) : WeatherRepository {

    override suspend fun getWeatherCurrentByCoordinatesAsync(
        lat: Double, lon: Double
    ): Deferred<WeatherCurrentResponse> {
        return weatherService.getWeatherCurrentByCoordinatesAsync(lat, lon)
    }

    override suspend fun getWeatherForecastByCoordinatesAsync(
        lat: Double, lon: Double
    ): Deferred<WeatherForecastResponse> {
        return weatherService.getWeatherForecastByCoordinatesAsync(lat, lon)
    }

    override suspend fun getWeatherForecastDBList() = weatherForecastDAO.getAllWeatherForecastList()

    override fun getWeatherForecastDBLiveData() = weatherForecastDAO.getAllWeatherForecast()

    override fun getWeatherForecastLastDBLiveData(): LiveData<WeatherForecastEntity> =
        weatherForecastDAO.getAllWeatherForecastLast()

    override suspend fun setWeatherForecastDB(readyResult: WeatherForecastResponse) {
        weatherForecastDAO.insertWeatherForecast(readyResult.mapToWeatherForecastEntity())
    }
}