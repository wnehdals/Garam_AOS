package com.jdm.garam.ui.event

import com.jdm.garam.R
import com.jdm.garam.base.ViewBindingActivity
import com.jdm.garam.data.response.campaign.Event
import com.jdm.garam.databinding.ActivityEventBinding
import com.jdm.garam.state.BaseState
import com.jdm.garam.util.CAMPAIGN
import com.jdm.garam.util.CAMPAIGN_TITLE
import org.koin.androidx.viewmodel.ext.android.viewModel
class EventActivity : ViewBindingActivity<ActivityEventBinding>() {
    override val layoutId: Int
        get() = R.layout.activity_event

    private val eventAdapter = EventAdapter()
    private val viewModel: EventViewModel by viewModel()
    override fun initView() {
        with(binding) {
            eventRecyclerview.adapter = eventAdapter
        }
        var campaignId = intent.getStringExtra(CAMPAIGN)?: "0"
        var campaignTitle = intent.getStringExtra(CAMPAIGN_TITLE)?: ""
        setBaseAppBar(campaignTitle)
        setBackKey()
        viewModel.getEventList(campaignId)

    }
    override fun subscribe() {
        viewModel.eventState.observe(this, {
            when (it) {
                is BaseState.Loading -> showProgressDialog()
                is BaseState.Success<*> -> {
                    hideProgressDialog()
                    eventAdapter.addData(it.SuccessResp as MutableList<Event>)
                }
                is BaseState.Fail<*> -> {
                    hideProgressDialog()
                    eventAdapter.addData(it.FailResp as MutableList<Event>)
                }
            }
        })
    }
}