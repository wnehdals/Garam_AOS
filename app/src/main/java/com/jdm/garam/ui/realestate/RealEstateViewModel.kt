package com.jdm.garam.ui.realestate

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jdm.garam.base.ViewModelBase
import com.jdm.garam.data.repository.RealEstateRepository
import com.jdm.garam.data.repository.RealEstateRepositoryImpl
import com.jdm.garam.data.repository.ScheduleRepositoryImpl
import com.jdm.garam.state.BaseState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

class RealEstateViewModel(private val repository: RealEstateRepository): ViewModelBase() {
    private val _realEstateState = MutableLiveData<BaseState>(BaseState.Uninitialized)
    val realEstateState: LiveData<BaseState> get() = _realEstateState

    fun getRealEstateData(serviceKey: String, lawdCd: Int, dealYMD: Int) {
        repository.getRealEstateData(serviceKey, lawdCd, dealYMD)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe{ _realEstateState.value = BaseState.Loading }
            .subscribe({
                when(it) {
                    is RealEstateRepositoryImpl.Result.Success<*> -> _realEstateState.value =
                        BaseState.Success(it.data)
                    is RealEstateRepositoryImpl.Result.Fail<*> -> _realEstateState.value =
                        BaseState.Fail(it.data)
                }
            },{

            })
            .addTo(compositeDisposable)

    }
}