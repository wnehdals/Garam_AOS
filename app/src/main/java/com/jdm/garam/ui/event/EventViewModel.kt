package com.jdm.garam.ui.event

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jdm.garam.base.ViewModelBase
import com.jdm.garam.data.repository.CampaignRepository
import com.jdm.garam.data.repository.CampaignRepositoryImpl
import com.jdm.garam.data.response.campaign.Event
import com.jdm.garam.state.BaseState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

class EventViewModel(private val repository: CampaignRepository): ViewModelBase() {
    private val _eventState = MutableLiveData<BaseState>(BaseState.Uninitialized)
    val eventState: LiveData<BaseState> get() = _eventState

    fun getEventList(campaignId: String) {
        _eventState.value = BaseState.Loading
        repository.getCampaign(campaignId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                when (it) {
                    is CampaignRepositoryImpl.Result.Success<*> -> _eventState.value =
                        BaseState.Success(it.data as MutableList<Event>)
                    is CampaignRepositoryImpl.Result.Fail<*> -> _eventState.value =
                        BaseState.Fail(it.data as MutableList<Event>)
                }
            },{

            })
            .addTo(compositeDisposable)
    }
}