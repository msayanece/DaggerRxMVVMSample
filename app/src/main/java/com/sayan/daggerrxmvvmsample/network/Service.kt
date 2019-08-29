package com.sayan.daggerrxmvvmsample.network

import com.sayan.weatherapptest.models.CurrentWeather
import retrofit2.http.GET
import retrofit2.http.Query

interface Service {

    /*/data/2.5/weather?q=london&AppID=d5d149f02e3ceda7485fd62e5ccd3583*/
    @GET("/data/2.5/weather")
    fun getCurrentWeatherInformation(
        @Query("AppID") appID: String,
        @Query("q") queryString: String
    ): retrofit2.Call<CurrentWeather>
}