package com.jdm.garam.di

import com.jdm.garam.data.datasource.BusDataSource
import com.jdm.garam.data.datasource.CoronaDataSource
import com.jdm.garam.data.datasource.RemoteBusDataSource
import com.jdm.garam.data.datasource.RemoteCoronaDataSource
import com.jdm.garam.data.repository.BusRepository
import com.jdm.garam.data.repository.BusRepositoryImpl
import com.jdm.garam.data.repository.CoronaRepository
import com.jdm.garam.data.repository.CoronaRepositoryImpl
import com.jdm.garam.ui.bus.station.BusStationViewModel
import com.jdm.garam.ui.bus.type.BusTypeViewModel
import com.jdm.garam.ui.home.HomeViewModel
import com.jdm.garam.ui.main.MainViewModel
import com.jdm.garam.util.MenuChangeEventBus
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel { MainViewModel() }
    viewModel { HomeViewModel(get()) }
    viewModel { BusTypeViewModel(get()) }
    viewModel { BusStationViewModel() }
    single { MenuChangeEventBus() }

    single<CoronaRepository> { CoronaRepositoryImpl(get()) }
    single<BusRepository> { BusRepositoryImpl(get()) }

    single<BusDataSource> { RemoteBusDataSource() }
    single<CoronaDataSource> { RemoteCoronaDataSource() }
}