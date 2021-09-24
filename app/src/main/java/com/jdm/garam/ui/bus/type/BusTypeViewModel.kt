package com.jdm.garam.ui.bus.type

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jdm.garam.base.ViewModelBase
import com.jdm.garam.data.repository.BusRepository
import com.jdm.garam.data.repository.BusRepositoryImpl
import com.jdm.garam.data.response.bus.Bus
import com.jdm.garam.state.BaseState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

class BusTypeViewModel(private val repository: BusRepository): ViewModelBase() {
    private val _busState = MutableLiveData<BaseState>(BaseState.Uninitialized)
    val busState: LiveData<BaseState> get() = _busState

    fun getBusList(type: Int) {
        _busState.value = BaseState.Loading
        repository.getBusData(type)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                when (it) {
                    is BusRepositoryImpl.Result.Success<*> -> _busState.value =
                        BaseState.Success(it.data as MutableList<*>)
                    is BusRepositoryImpl.Result.Fail<*> -> _busState.value =
                        BaseState.Fail((it.data as MutableList<*>))
                }
            }, {

            })
            .addTo(compositeDisposable)
    }
}