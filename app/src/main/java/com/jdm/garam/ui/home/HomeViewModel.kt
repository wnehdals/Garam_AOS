package com.jdm.garam.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jdm.garam.base.ViewModelBase
import com.jdm.garam.data.repository.CoronaRepository
import com.jdm.garam.data.repository.CoronaRepositoryImpl
import com.jdm.garam.data.response.CoronaStatistic
import com.jdm.garam.state.BaseState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

class HomeViewModel(private val repository: CoronaRepository): ViewModelBase() {
    private val _coronaStatisticState = MutableLiveData<BaseState>(BaseState.Uninitialized)
    val coronaStatisticState: LiveData<BaseState> get() = _coronaStatisticState


    fun getCoronaStatistic() {
        repository.getCoronaStatistic()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                when(it) {
                    is CoronaRepositoryImpl.Result.Success<*> -> _coronaStatisticState.value = BaseState.Success((it.data as CoronaStatistic))
                    is CoronaRepositoryImpl.Result.Fail<*> -> _coronaStatisticState.value = BaseState.Fail((it.data as CoronaStatistic))
                }
            },{

            })
            .addTo(compositeDisposable)
    }



}