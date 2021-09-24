package com.jdm.garam.data.datasource

import com.jdm.garam.data.response.bus.Bus
import com.jdm.garam.data.response.bus.BusResp
import io.reactivex.rxjava3.core.Single

interface BusDataSource {
    fun getBusData(): Single<BusResp>
}