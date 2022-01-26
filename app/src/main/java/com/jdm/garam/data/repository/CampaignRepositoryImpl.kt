package com.jdm.garam.data.repository

import com.jdm.garam.data.datasource.CampaignDataSource
import com.jdm.garam.data.datasource.RemoteCampaignDataSource
import com.jdm.garam.data.response.campaign.CampaignResp
import io.reactivex.rxjava3.core.Single

class CampaignRepositoryImpl(private val remoteCampaignDataSource: CampaignDataSource): CampaignRepository {
    override fun getCampaign(campaignId: String): Single<Result> {
        return Single.create { subsriber ->
            remoteCampaignDataSource.getCampaign(campaignId)
                .subscribe({
                    subsriber.onSuccess(Result.Success(it.body))
                }, {
                    subsriber.onSuccess(Result.Fail(CampaignResp().body))
                })
        }
    }

    sealed class Result {

        data class Success<T>(
            val data: T? = null
        ) : Result()

        data class Fail<T>(
            val data: T? = null
        ) : Result()
    }
}