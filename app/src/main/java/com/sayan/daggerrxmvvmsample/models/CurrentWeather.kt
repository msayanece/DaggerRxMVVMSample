package com.sayan.weatherapptest.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class CurrentWeather (

//    @SerializedName("coord")
//    @Expose
//    var coord: Coord,
//    @SerializedName("weather")
//    @Expose
//    var weather: List<Weather>,
    @SerializedName("base")
    @Expose
    var base: String = "",
//    @SerializedName("main")
//    @Expose
//    var main: Main,
    @SerializedName("visibility")
    @Expose
    var visibility: Int = 0,
//    @SerializedName("wind")
//    @Expose
//    var wind: Wind,
//    @SerializedName("clouds")
//    @Expose
//    var clouds: Clouds,
    @SerializedName("dt")
    @Expose
    var dt: Int = 0,
//    @SerializedName("sys")
//    @Expose
//    var sys: Sys,
    @SerializedName("timezone")
    @Expose
    var timezone: Int = 0,
    @SerializedName("id")
    @Expose
    var id: Int = 0,
    @SerializedName("name")
    @Expose
    var name: String = "",
    @SerializedName("cod")
    @Expose
    var cod: String = "",
    @SerializedName("message")
    @Expose
    var message: String = ""
    )
