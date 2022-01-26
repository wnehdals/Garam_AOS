package com.jdm.garam.data.datasource

import com.jdm.garam.data.api.Api
import com.jdm.garam.data.response.campaign.CampaignResp
import io.reactivex.rxjava3.core.Single

class RemoteCampaignDataSource(private val api: Api): CampaignDataSource {
    override fun getCampaign(campaignId: String): Single<CampaignResp> {
        return api.getCampaign(campaignId)
    }
}