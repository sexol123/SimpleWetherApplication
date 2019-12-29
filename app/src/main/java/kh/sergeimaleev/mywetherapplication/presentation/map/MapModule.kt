package kh.sergeimaleev.mywetherapplication.presentation.map

import org.koin.android.ext.koin.androidApplication
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mapModule = module {
    viewModel { MapViewModel(get()) }
    viewModel { LocationsViewModel(this.androidApplication()) }
}