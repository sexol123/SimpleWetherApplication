package kh.sergeimaleev.mywetherapplication.di

import kh.sergeimaleev.mywetherapplication.presentation.history.historyModule
import kh.sergeimaleev.mywetherapplication.presentation.main.mainModule
import kh.sergeimaleev.mywetherapplication.presentation.map.mapModule

val uiModule = listOf(
    mainModule,
    mapModule,
    historyModule
)