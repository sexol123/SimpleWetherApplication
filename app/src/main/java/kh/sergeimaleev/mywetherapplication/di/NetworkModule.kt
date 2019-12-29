package kh.sergeimaleev.mywetherapplication.di

import android.app.Application
import kh.sergeimaleev.mywetherapplication.data.rest.client.RestClient
import kh.sergeimaleev.mywetherapplication.data.rest.client.RestClientImpl
import kh.sergeimaleev.mywetherapplication.data.rest.service.WeatherService
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val networkModule = module {
    single { provideRestClient(this.androidApplication()) }
    single { provideWeatherService(get()) }
}

fun provideWeatherService(restClient: RestClient): WeatherService {
    return restClient.createRemoteDataSourceFrom(WeatherService::class.java)
}

fun provideRestClient(app: Application): RestClient {
    return RestClientImpl(app)
}
