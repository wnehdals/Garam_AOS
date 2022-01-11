package com.jdm.garam.viewmodel

import com.jdm.garam.base.RxViewModelTest
import com.jdm.garam.data.datasource.CoronaDataSource
import com.jdm.garam.data.response.coronastep.CoronaStep
import com.jdm.garam.state.BaseState
import com.jdm.garam.ui.home.HomeViewModel
import com.jdm.garam.util.NetworkUseCase
import kr.co.grow.app.data.protocol.TestCoronaDataSource
import org.junit.Before
import org.junit.Test
import org.koin.test.inject

internal class HomeViewModelTest: RxViewModelTest() {
    private val homeViewModel by inject<HomeViewModel>()
    private val datasource by inject<CoronaDataSource>()

    fun changeDataSourceUseCase(useCase: NetworkUseCase) {
        (datasource as TestCoronaDataSource).networkUseCase = useCase
    }
    @Before
    fun init() {

    }
    @Test
    fun `get fail CoronaStep response case`() {
        changeDataSourceUseCase(NetworkUseCase.Fail)
        val testObservable = homeViewModel.coronaStepState.test()
        homeViewModel.getCoronaStep()

        testObservable.assertValueSequence(
            listOf(
                BaseState.Uninitialized,
                BaseState.Fail(CoronaStep())
            )
        )
    }
    @Test
    fun `get success CoronaStep response case`() {
        changeDataSourceUseCase(NetworkUseCase.Success)
        val testObservable = homeViewModel.coronaStepState.test()
        homeViewModel.getCoronaStep()

        val response = CoronaStep("2021.01.01 ~ 2021.12.31","4단")

        testObservable.assertValueSequence(
            listOf(
                BaseState.Uninitialized,
                BaseState.Success(response)
            )
        )
    }
}