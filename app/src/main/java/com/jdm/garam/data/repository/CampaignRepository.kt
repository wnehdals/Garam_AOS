package com.jdm.garam.data.repository

import com.jdm.garam.data.response.campaign.CampaignResp
import io.reactivex.rxjava3.core.Single

interface CampaignRepository {
    fun getCampaign(campaignId: String): Single<CampaignRepositoryImpl.Result>
}