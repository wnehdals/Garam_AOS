package com.jdm.garam.data.repository

import io.reactivex.rxjava3.core.Single

interface CampaignRepository {
    fun getCampaign(campaignId: String): Single<CampaignRepositoryImpl.Result>
}