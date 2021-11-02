package com.jdm.garam.di

import com.jdm.garam.data.datasource.CoronaDataSource
import com.jdm.garam.data.repository.CoronaRepository
import com.jdm.garam.data.repository.CoronaRepositoryImpl
import com.jdm.garam.ui.SplashViewModel
import kotlinx.coroutines.Dispatchers
import kr.co.grow.app.data.protocol.TestCoronaDataSource
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

internal val appTestModule = module {
    viewModel { SplashViewModel(get()) }
    single { Dispatchers.IO }
    single { Dispatchers.Main }
    //datasource
    single<CoronaDataSource> { TestCoronaDataSource() }

    //repository
    single<CoronaRepository> { CoronaRepositoryImpl(get()) }
}