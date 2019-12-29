package kh.sergeimaleev.mywetherapplication.di

import kh.sergeimaleev.mywetherapplication.data.local.db.databaseModule
import kh.sergeimaleev.mywetherapplication.data.repository.repositoriesModule
import kh.sergeimaleev.mywetherapplication.domain.di.interactorModule

val appModule =
    uiModule + networkModule + interactorModule + repositoriesModule + databaseModule
