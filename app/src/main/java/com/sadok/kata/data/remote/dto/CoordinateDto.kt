package com.sadok.kata.data.remote.dto

import com.google.gson.annotations.SerializedName

class CoordinateDto(
    @SerializedName("lat")
    val latitude: Double,
    @SerializedName("lon")
    val longitude: Double
)