package com.jdm.garam.viewmodel

import com.jdm.garam.base.RxViewModelTest
import com.jdm.garam.data.datasource.CoronaDataSource
import com.jdm.garam.data.repository.CoronaRepository
import com.jdm.garam.data.response.version.Version
import com.jdm.garam.data.response.version.VersionResp
import com.jdm.garam.state.BaseState
import com.jdm.garam.ui.SplashViewModel
import com.jdm.garam.util.NetworkUseCase
import kr.co.grow.app.data.protocol.TestCoronaDataSource
import org.junit.Before
import org.junit.Test
import org.koin.test.inject

internal class SplashViewModelTest: RxViewModelTest() {
    private val splashViewModel by inject<SplashViewModel>()
    private val datasource by inject<CoronaDataSource>()

    fun changeDataSourceUseCase(useCase: NetworkUseCase) {
        (datasource as TestCoronaDataSource).networkUseCase = useCase
    }
    @Before
    fun init() {

    }
    @Test
    fun `get fail response case`() {
        changeDataSourceUseCase(NetworkUseCase.Fail)
        val testObservable = splashViewModel.versionState.test()
        splashViewModel.getVersion()

        testObservable.assertValueSequence(
            listOf(
                BaseState.Uninitialized,
                BaseState.Fail(Version())
            )
        )
    }
    @Test
    fun `get success response case`() {
        changeDataSourceUseCase(NetworkUseCase.Success)
        val testObservable = splashViewModel.versionState.test()
        splashViewModel.getVersion()

        val version = Version(1.0f, false)
        testObservable.assertValueSequence(
            listOf(
                BaseState.Uninitialized,
                BaseState.Success(version)
            )
        )
    }
}