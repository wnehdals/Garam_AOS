package com.jdm.garam.ui.phonebook

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jdm.garam.base.ViewModelBase
import com.jdm.garam.data.repository.CampaignRepositoryImpl
import com.jdm.garam.data.repository.PhoneBookRepository
import com.jdm.garam.data.repository.RealEstateRepository
import com.jdm.garam.data.response.campaign.Event
import com.jdm.garam.data.response.phonebook.PhoneBook
import com.jdm.garam.state.BaseState
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.launch

class PhoneBookViewModel(private val repository: PhoneBookRepository): ViewModelBase() {
    private val _phoneBookState = MutableLiveData<MutableList<PhoneBook>>()
    val phoneBookState: LiveData<MutableList<PhoneBook>> get() = _phoneBookState

    fun getEventList(serviceKey: String) {
        viewModelScope.launch {
            repository.getPhoneBookData(serviceKey=serviceKey,
                onSuccess = {
                    _phoneBookState.value = it
                }, onError = {

                }
            )
        }
    }
}