package com.jdm.garam.ui.realestate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.jdm.garam.base.ViewModelBase
import com.jdm.garam.data.repository.RealEstateRepository
import com.jdm.garam.data.repository.RealEstateRepositoryImpl
import com.jdm.garam.data.repository.ScheduleRepositoryImpl
import com.jdm.garam.data.response.Building
import com.jdm.garam.state.BaseState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch

class RealEstateViewModel(private val repository: RealEstateRepository): ViewModelBase() {
    private var _buildingList = MutableLiveData<PagingData<Building>>()
    val buildingList: LiveData<PagingData<Building>> get() = _buildingList


    fun getAptRentData(serviceKey: String) {
        _buildingList = repository.getRealEstateData(serviceKey).cachedIn(viewModelScope)
            .asLiveData()
            .let { it as MutableLiveData<PagingData<Building>> }

    }
}