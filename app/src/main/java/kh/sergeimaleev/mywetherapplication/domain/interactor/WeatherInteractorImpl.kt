package kh.sergeimaleev.mywetherapplication.domain.interactor

import androidx.lifecycle.LiveData
import kh.sergeimaleev.mywetherapplication.data.local.db.entities.WeatherForecastEntity
import kh.sergeimaleev.mywetherapplication.data.rest.model.WeatherCurrentResponse
import kh.sergeimaleev.mywetherapplication.data.rest.model.forecastmodel.WeatherForecastResponse
import kh.sergeimaleev.mywetherapplication.domain.exceptions.SimpleExceptionHandler
import kh.sergeimaleev.mywetherapplication.domain.repository.WeatherRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherInteractorImpl(
    private val weatherRepository: WeatherRepository
) : BaseInteraction(), WeatherInteractor {
    override val weatherForecastDatabaseLiveData: LiveData<List<WeatherForecastEntity>>
        get() = weatherRepository.getWeatherForecastDBLiveData()

    override val weatherLastForecastDatabaseLiveData: LiveData<WeatherForecastEntity>
        get() = weatherRepository.getWeatherForecastLastDBLiveData()

    override fun getWeatherCurrentByCoordinates(
        lat: Double,
        lon: Double,
        onSuccess: (WeatherCurrentResponse) -> Unit,
        onFail: (error: Throwable) -> Unit
    ) {

        val handlerError = SimpleExceptionHandler { error ->
            onFail(error)
        }

        mScope.launch(Dispatchers.IO + handlerError) {
            val result = weatherRepository.getWeatherCurrentByCoordinatesAsync(lat, lon)
            onSuccess(result.await())
        }
    }

    override fun getWeatherForecastByCoordinates(
        lat: Double,
        lon: Double,
        onSuccess: (WeatherForecastResponse) -> Unit,
        onFail: (error: Throwable) -> Unit
    ) {
        val handlerError = SimpleExceptionHandler { error ->
            onFail(error)
        }

        mScope.launch(Dispatchers.IO + handlerError) {
            val result = weatherRepository.getWeatherForecastByCoordinatesAsync(lat, lon)
                .await()

            setWeatherForecastToDatabase(result, onFail)
            onSuccess(result)
        }
    }

    override fun setWeatherForecastToDatabase(
        readyResult: WeatherForecastResponse,
        onFail: (error: Throwable) -> Unit
    ) {
        val handlerError = SimpleExceptionHandler { error ->
            onFail(error)
        }

        mScope.launch(Dispatchers.IO + handlerError) {
            weatherRepository.setWeatherForecastDB(readyResult)
        }
    }

    override fun getWeatherForecastFromDatabase(
        onSuccess: (List<WeatherForecastEntity>) -> Unit,
        onFail: (error: Throwable) -> Unit
    ) {
        val handlerError = SimpleExceptionHandler { error ->
            onFail(error)
        }

        mScope.launch(Dispatchers.IO + handlerError) {
            val result = weatherRepository.getWeatherForecastDBList()

            onSuccess(result)
        }
    }
}