package com.jdm.garam.di

import com.jdm.garam.data.datasource.*
import com.jdm.garam.data.repository.*
import com.jdm.garam.ui.realestate.RealEstateViewModel
import com.jdm.garam.ui.SplashViewModel
import com.jdm.garam.ui.bus.station.BusStationViewModel
import com.jdm.garam.ui.bus.type.BusTypeViewModel
import com.jdm.garam.ui.calendar.ScheduleViewModel
import com.jdm.garam.ui.event.EventViewModel
import com.jdm.garam.ui.home.HomeViewModel
import com.jdm.garam.ui.main.MainViewModel
import com.jdm.garam.util.BUS
import com.jdm.garam.util.MenuChangeEventBus
import com.jdm.garam.util.REAL_ESTATE
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    viewModel { MainViewModel() }
    viewModel { HomeViewModel(get()) }
    viewModel { BusTypeViewModel(get()) }
    viewModel { BusStationViewModel() }
    viewModel { ScheduleViewModel(get()) }
    viewModel { SplashViewModel(get()) }
    viewModel { RealEstateViewModel(get()) }
    viewModel { EventViewModel(get()) }
    single { MenuChangeEventBus() }

    single<CoronaRepository> { CoronaRepositoryImpl(get()) }
    single<BusRepository> { BusRepositoryImpl(get()) }
    single<ScheduleRepository> { ScheduleRepositoryImpl(get()) }
    single<RealEstateRepository> { RealEstateRepositoryImpl(get())}
    single<CampaignRepository> {CampaignRepositoryImpl(get())}

    single<BusDataSource> { RemoteBusDataSource(get(named(BUS))) }
    single<CoronaDataSource> { RemoteCoronaDataSource(get(named(BUS))) }
    single<ScheduleDataSource> { RemoteScheduleDataSource(get(named(BUS))) }
    single<RealEstateDataSource> { RemoteRealEstateDataSource(get(named(REAL_ESTATE)))}
    single<CampaignDataSource> { RemoteCampaignDataSource(get(named(BUS))) }
}