package com.jdm.garam.data.datasource

import com.jdm.garam.data.api.Api
import com.jdm.garam.data.response.bus.BusResp
import io.reactivex.rxjava3.core.Single

class RemoteBusDataSource(private val api: Api): BusDataSource {
    override fun getBusData(): Single<BusResp> {
        return api.getBusData()
    }


}