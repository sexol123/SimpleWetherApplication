package kh.sergeimaleev.mywetherapplication.presentation.history

import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val historyModule = module {
    viewModel { HistoryViewModule(get()) }
}