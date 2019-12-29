package kh.sergeimaleev.mywetherapplication.presentation.map

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.maps.model.LatLng
import kh.sergeimaleev.mywetherapplication.data.local.db.entities.WeatherForecastEntity
import kh.sergeimaleev.mywetherapplication.domain.interactor.WeatherInteractor
import kh.sergeimaleev.mywetherapplication.presentation.base.BaseViewModel

class MapViewModel(
    private val weatherInteractor: WeatherInteractor
) : BaseViewModel() {

    val screenState = MutableLiveData<MapScreenState>()
    val forecastWeather = MutableLiveData<WeatherForecastEntity>()

    fun getWeatherForecast(latLng: LatLng) {
        weatherInteractor.getWeatherForecastByCoordinates(
            latLng.latitude, latLng.longitude,
            onSuccess = {
                forecastWeather.postValue(it.mapToWeatherForecastEntity())
            },
            onFail = {
                Log.d("Weather", it.message.toString(), it.fillInStackTrace())
            }
        )
    }

    fun moveToPoint(latLng: LatLng) {
        screenState.value = MapScreenState.MovedToLocation(latLng)
    }
}