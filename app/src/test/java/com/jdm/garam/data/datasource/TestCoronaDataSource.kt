package kr.co.grow.app.data.protocol

import com.jdm.garam.data.datasource.CoronaDataSource
import com.jdm.garam.data.response.CoronaStatistic
import com.jdm.garam.data.response.coronastep.CoronaStep
import com.jdm.garam.data.response.coronastep.CoronaStepResp
import com.jdm.garam.data.response.version.Version
import com.jdm.garam.data.response.version.VersionResp
import com.jdm.garam.util.NetworkUseCase
import io.reactivex.rxjava3.core.Single

class TestCoronaDataSource : CoronaDataSource {
    var networkUseCase = NetworkUseCase.Success
    override fun getCoronaStatistic(): Single<CoronaStatistic> {
        return Single.never()
    }

    override fun getVersion(): Single<VersionResp> {
        val version = Version(1.0f, false)
        return when (networkUseCase) {

            NetworkUseCase.Success -> Single.create { subscriber ->
                val response = VersionResp(200, version)
                subscriber.onSuccess(response)
            }
            NetworkUseCase.Fail -> Single.create { subscriber ->
                subscriber.onSuccess(VersionResp())
            }

        }
    }

    override fun getCoronaStep(): Single<CoronaStepResp> {
        return when (networkUseCase) {
            NetworkUseCase.Success -> Single.create{ subscriber ->
                val response = CoronaStepResp().apply {
                    statusCode = 200
                    body = CoronaStep().apply {
                        duration = "2021.01.01 ~ 2021.12.31"
                        step = "4단"
                    }
                }
                subscriber.onSuccess(response)
            }
            NetworkUseCase.Fail -> Single.create { subscriber ->
                subscriber.onSuccess(CoronaStepResp())
            }
        }
    }
}