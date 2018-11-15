package com.mgaebz.cromulence

import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val koinApplicationModule = module {
    single { MessageRepository() }

    viewModel { MessageViewModel(get()) }
}