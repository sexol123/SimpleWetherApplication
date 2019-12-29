package kh.sergeimaleev.mywetherapplication.domain.di

import kh.sergeimaleev.mywetherapplication.domain.interactor.WeatherInteractor
import kh.sergeimaleev.mywetherapplication.domain.interactor.WeatherInteractorImpl
import kh.sergeimaleev.mywetherapplication.domain.repository.WeatherRepository
import org.koin.dsl.module

val interactorModule = module {
    single { provideWeatherInteractor(get()) }
}

fun provideWeatherInteractor(weatherRepository: WeatherRepository): WeatherInteractor {
    return WeatherInteractorImpl(weatherRepository)
}