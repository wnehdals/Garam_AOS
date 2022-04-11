package com.jdm.garam.data.datasource

import com.jdm.garam.data.api.Api
import com.jdm.garam.data.response.CoronaStatistic
import com.jdm.garam.data.response.version.VersionResp
import io.reactivex.rxjava3.core.Single
import org.jsoup.Jsoup
import org.jsoup.select.Elements

class RemoteCoronaDataSource(private val api: Api): CoronaDataSource {
    override fun getCoronaStatistic(): Single<CoronaStatistic> {

        return Single.create { subscriber ->
            try {
                val coronaDataList = mutableListOf<String>()
                val url = "https://www.samcheok.go.kr/02179/02696.web"
                val doc = Jsoup.connect(url).timeout(1000 * 10).get()  //타임아웃 10초
                val contentData : Elements = doc.select("div.info1 div div div ul li p span")
                for(i in contentData.indices step 2) {
                    coronaDataList.add(contentData[i].childNode(0).toString())
                }
                if (coronaDataList.size >= 4) {
                    subscriber.onSuccess(CoronaStatistic(coronaDataList[0], coronaDataList[1], coronaDataList[2], coronaDataList[3]))
                } else {
                    subscriber.onSuccess(CoronaStatistic())
                }
            } catch (e: Exception) {
                subscriber.onError(e)
            }
        }
    }

    override fun getVersion(): Single<VersionResp> {
        return api.getVersion()
    }

}
