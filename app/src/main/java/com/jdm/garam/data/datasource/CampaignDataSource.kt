package com.jdm.garam.data.datasource

import com.jdm.garam.data.response.campaign.CampaignResp
import com.jdm.garam.data.response.coronastep.CoronaStepResp
import io.reactivex.rxjava3.core.Single

interface CampaignDataSource {
    fun getCampaign(campaignId: String): Single<CampaignResp>
}