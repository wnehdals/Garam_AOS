package com.jdm.garam.data.datasource

import com.jdm.garam.data.response.CoronaStatistic
import io.reactivex.rxjava3.core.Single
import org.jsoup.Jsoup
import org.jsoup.select.Elements

class RemoteCoronaDataSource: CoronaDataSource {
    override fun getCoronaStatistic(): Single<CoronaStatistic> {

        return Single.create { subscriber ->
            try {
                val coronaDataList = mutableListOf<String>()
                val url = "https://www.samcheok.go.kr/02179/02696.web"
                val doc = Jsoup.connect(url).timeout(1000 * 10).get()  //타임아웃 10초
                val contentData : Elements = doc.select("div.info1 div div div ul li p span")
                contentData.removeAt(contentData.size - 1)
                for(i in contentData.indices step 2) {
                    coronaDataList.add(contentData[i].childNode(0).toString())
                }
                var coronaStatistic = CoronaStatistic()
                coronaStatistic.apply {
                    infected = coronaDataList[0]
                    cured = coronaDataList[1]
                    sum = coronaDataList[2]
                    inspected = coronaDataList[3]
                    negative = coronaDataList[4]
                    selfQuarantine = coronaDataList[5]
                }
                subscriber.onSuccess(coronaStatistic)
            } catch (e: Exception) {
                subscriber.onError(e)
            }


        }
    }
}
