package com.jdm.garam.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jdm.garam.base.ViewModelBase
import com.jdm.garam.data.repository.CoronaRepository
import com.jdm.garam.data.repository.CoronaRepositoryImpl
import com.jdm.garam.data.repository.ScheduleRepositoryImpl
import com.jdm.garam.state.BaseState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

class SplashViewModel(private val repository: CoronaRepository): ViewModelBase() {
    private val _versionState = MutableLiveData<BaseState>(BaseState.Uninitialized)
    val versionState: LiveData<BaseState> get() = _versionState

    fun getVersion() {
        repository.getVersion()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                when(it) {
                    is CoronaRepositoryImpl.Result.Success<*> -> {
                        _versionState.value =
                            BaseState.Success(it.data)
                    }
                    is CoronaRepositoryImpl.Result.Fail<*> -> {
                        _versionState.value =
                            BaseState.Success(it.data)
                    }

                }
            },{

            })

    }
}