package com.jdm.garam.data.datasource

import com.jdm.garam.data.response.campaign.CampaignResp
import io.reactivex.rxjava3.core.Single

interface CampaignDataSource {
    fun getCampaign(campaignId: String): Single<CampaignResp>
}