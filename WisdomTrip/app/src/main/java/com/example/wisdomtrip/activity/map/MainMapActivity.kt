package com.example.wisdomtrip.activity.map

import android.os.Bundle
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import com.example.wisdomtrip.activity.base.BaseMapActivity
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*


class MainMapActivity : BaseMapActivity() {


    private var locationClient: AMapLocationClient? = null

    private var locationOption: AMapLocationClientOption? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initLocation()
    }

    override fun initLocation() {
        locationClient = AMapLocationClient(applicationContext)
        locationClient?.setLocationListener(locationListener)
        locationOption = AMapLocationClientOption()
        /**
         * 设置定位场景，目前支持三种场景（签到、出行、运动，默认无场景）
         */
        locationOption?.locationPurpose = AMapLocationClientOption.AMapLocationPurpose.SignIn
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式
        locationOption?.locationMode = AMapLocationClientOption.AMapLocationMode.Hight_Accuracy
        locationOption?.isOnceLocationLatest = true
        locationOption?.interval = 1000L
        locationOption?.isNeedAddress = true
        locationOption?.httpTimeOut = 3000L
        locationOption?.isLocationCacheEnable = false
        if (locationClient != null) {
            locationClient?.setLocationOption(locationOption)
            locationClient?.stopLocation()
            locationClient?.startLocation()
        }
    }


    private val locationListener = AMapLocationListener { amapLocation ->
        if (amapLocation != null) {
            if (amapLocation.errorCode == 0) {
                amapLocation.locationType//获取当前定位结果来源，如网络定位结果，详见定位类型表
                amapLocation.latitude//获取纬度
                amapLocation.longitude//获取经度
                amapLocation.accuracy//获取精度信息
                amapLocation.address//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                amapLocation.country//国家信息
                amapLocation.province//省信息
                amapLocation.city//城市信息
                amapLocation.district//城区信息
                amapLocation.street//街道信息
                amapLocation.streetNum//街道门牌号信息
                amapLocation.cityCode//城市编码
                amapLocation.adCode//地区编码
                amapLocation.aoiName//获取当前定位点的AOI信息
                amapLocation.buildingId//获取当前室内定位的建筑物Id
                amapLocation.floor//获取当前室内定位的楼层
                amapLocation.gpsAccuracyStatus//获取GPS的当前状态
                //获取定位时间
                val df = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
                val date = Date(amapLocation.time)
                df.format(date)
                Log.d(
                    "TAG", " \n" +
                            "locationType:${amapLocation.locationType}\n" +
                            "latitude:${amapLocation.latitude}\n" +
                            "accuracy:${amapLocation.accuracy}\n" +
                            "address:${amapLocation.address}\n" +
                            "country:${amapLocation.country}\n" +
                            "province:${amapLocation.province}\n" +
                            "city:${amapLocation.city}\n" +
                            "district:${amapLocation.district}\n" +
                            "street:${amapLocation.street}\n" +
                            "streetNum:${amapLocation.streetNum}\n" +
                            "cityCode:${amapLocation.cityCode}\n" +
                            "adCode:${amapLocation.adCode}\n" +
                            "aoiName:${amapLocation.aoiName}\n" +
                            "buildingId:${amapLocation.buildingId}\n" +
                            "floor:${amapLocation.floor}\n" +
                            "gpsAccuracyStatus:${amapLocation.gpsAccuracyStatus}\n" +
                            "date:${df.format(date)}"
                )
            } else {
                Log.e("TAG", "errorCode:${amapLocation.errorCode}\nerrorInfo:${amapLocation.errorInfo}")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        locationClient?.stopLocation()
    }
}