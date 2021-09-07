package com.jdm.garam.ui.bus.station

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jdm.garam.base.ViewModelBase
import com.jdm.garam.data.response.bus.Bus
import com.jdm.garam.data.response.bus.BusType
import com.jdm.garam.state.BaseState
import com.jdm.garam.util.STATION_TYPE_DESC
import com.jdm.garam.util.STATION_TYPE_END
import com.jdm.garam.util.STATION_TYPE_MIDDLE
import com.jdm.garam.util.STATION_TYPE_START
import kotlinx.coroutines.launch

class BusStationViewModel: ViewModelBase() {
    private val _busTypeState = MutableLiveData<BaseState>(BaseState.Uninitialized)
    val busTypeState: LiveData<BaseState> get() = _busTypeState

    fun getBusTypeList(bus: Bus) {
        viewModelScope.launch {
            _busTypeState.value = BaseState.Loading
            val list = mutableListOf<BusType>()
            var decBusType = BusType(
                type = STATION_TYPE_DESC,
                id = bus.number,
                description = bus.title,
                startStation = bus.startTime,
                endStation = bus.endTime,
            )
            list.add(decBusType)

            for (i in bus.route.indices) {
                if (i == 0)//첫번째 항목
                    list.add(BusType(type = STATION_TYPE_START, stations = bus.route[i]))
                else if (i == bus.route.size - 1) //마지막항목
                    list.add(BusType(type = STATION_TYPE_END, stations = bus.route[i]))
                else
                    list.add(BusType(type = STATION_TYPE_MIDDLE, stations = bus.route[i]))
            }
            if (list.isNotEmpty())
                _busTypeState.value = BaseState.Success(list)
            else
                _busTypeState.value = BaseState.Fail(list)


        }
    }
}