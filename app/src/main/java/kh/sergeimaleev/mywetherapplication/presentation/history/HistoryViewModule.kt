package kh.sergeimaleev.mywetherapplication.presentation.history

import kh.sergeimaleev.mywetherapplication.domain.interactor.WeatherInteractor
import kh.sergeimaleev.mywetherapplication.presentation.base.BaseViewModel

class HistoryViewModule(
    weatherInteractor: WeatherInteractor
) : BaseViewModel() {
    var historyItems = weatherInteractor.weatherForecastDatabaseLiveData
}
