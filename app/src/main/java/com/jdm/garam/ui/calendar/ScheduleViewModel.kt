package com.jdm.garam.ui.calendar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jdm.garam.base.ViewModelBase
import com.jdm.garam.data.repository.ScheduleRepository
import com.jdm.garam.data.repository.ScheduleRepositoryImpl
import com.jdm.garam.state.BaseState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

class ScheduleViewModel(private val repository: ScheduleRepository): ViewModelBase() {
    private val _scheduleState = MutableLiveData<BaseState>(BaseState.Uninitialized)
    val scheduleState: LiveData<BaseState> get() = _scheduleState

    fun getScheduleData(month: String) {
        repository.getScheduleData(month)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                when(it) {
                    is ScheduleRepositoryImpl.Result.Success<*> -> _scheduleState.value =
                        BaseState.Success(it.data as List<*>)
                    is ScheduleRepositoryImpl.Result.Fail<*> -> _scheduleState.value =
                        BaseState.Success(it.data as List<*>)

                }
            },{

            })
            .addTo(compositeDisposable)

    }
}