package com.jdm.garam.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jdm.garam.base.ViewModelBase
import com.jdm.garam.data.repository.CoronaRepository
import com.jdm.garam.data.repository.CoronaRepositoryImpl
import com.jdm.garam.data.repository.ScheduleRepositoryImpl
import com.jdm.garam.state.BaseState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch

class SplashViewModel(private val repository: CoronaRepository) : ViewModelBase() {
    private val _versionState = MutableLiveData<BaseState>(BaseState.Uninitialized)
    val versionState: LiveData<BaseState> get() = _versionState

    fun getVersion() {
        viewModelScope.launch {
            repository.getVersion(
                onSuccess = {
                    _versionState.value = BaseState.Success(it)
                }, onError = {
                    _versionState.value = BaseState.Fail(it)
                }
            )
        }



    }
}