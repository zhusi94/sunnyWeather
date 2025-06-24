package com.sunnyweather.android.logic.model

import com.google.gson.annotations.SerializedName

data class PlaceResponse(val status: String,val info: String,val count: Number, val geocodes: List<Geocodes>)

data class Geocodes(
    val city: String,
    val location: String,
    @SerializedName("formatted_address") val address: String
)

data class Location(val lng: String, val lat: String)