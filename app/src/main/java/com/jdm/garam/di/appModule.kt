package com.jdm.garam.di

import com.jdm.garam.ui.main.MainViewModel
import com.jdm.garam.util.MenuChangeEventBus
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MainViewModel() }
    single { MenuChangeEventBus() }
}