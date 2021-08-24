package com.jdm.garam.data.datasource

import android.util.Log
import com.jdm.garam.data.response.Bus
import io.reactivex.rxjava3.core.Single
import org.jsoup.Jsoup
import org.jsoup.select.Elements

class RemoteBusDataSource: BusDataSource {
    override fun getBusData(url: String): Single<MutableList<Bus>> {
        return Single.create { subscriber ->
            try {
                val doc = Jsoup.connect(url).timeout(1000 * 10).get()  //타임아웃 10초
                val busNum : Elements = doc.select("div.tourlist2f1t3dl1 div div div div b.t1")
                val busDescription: Elements = doc.select("div.tourlist2f1t3dl1 div div div div div strong")
                val station : Elements = doc.select("div.tourlist2f1t3dl1 div div div div div span.t3")
                val startStation : Elements = doc.select("div.tourlist2f1t3dl1 div div div div div div span.t1")
                val startStationTime : Elements = doc.select("div.tourlist2f1t3dl1 div div div div div div span.dd")
                val startNameList = mutableListOf<String>()
                val endNameList = mutableListOf<String>()
                val startTimeList = mutableListOf<String>()
                val endTimeList = mutableListOf<String>()
                val busList = mutableListOf<Bus>()
                for(i in startStation.indices) {
                     if(i % 2 == 0) {
                         startNameList.add(startStation[i].childNode(0).toString())
                         startTimeList.add(startStationTime[i].childNode(0).toString())
                     } else {
                         endNameList.add(startStation[i].childNode(0).toString())
                         endTimeList.add(startStationTime[i].childNode(0).toString())
                     }
                }
                for(i in station.indices) {
                    val bus = Bus(
                        busNum[i].childNode(0).toString(),
                        busDescription[i].childNode(0).toString(),
                        station[i].childNode(0).toString().split("→").toMutableList(),
                        startNameList[i],
                        startTimeList[i],
                        endNameList[i],
                        endTimeList[i]
                    )
                    busList.add(bus)
                }
                subscriber.onSuccess(busList)

            } catch (e: Exception) {
                subscriber.onError(e)
            }
        }

    }
}