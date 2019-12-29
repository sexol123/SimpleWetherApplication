package kh.sergeimaleev.mywetherapplication.data.repository

import kh.sergeimaleev.mywetherapplication.data.local.db.AppDatabase
import kh.sergeimaleev.mywetherapplication.data.rest.service.WeatherService
import kh.sergeimaleev.mywetherapplication.domain.repository.WeatherRepository
import org.koin.dsl.module

val repositoriesModule = module {
    single { provideWeatherRepository(get(), get()) }
}

fun provideWeatherRepository(
    weatherService: WeatherService,
    appDatabase: AppDatabase
): WeatherRepository {
    return WeatherRepositoryImpl(weatherService, appDatabase.userClassDao())
}