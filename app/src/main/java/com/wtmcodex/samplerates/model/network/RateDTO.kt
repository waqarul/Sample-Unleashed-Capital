package com.wtmcodex.samplerates.model.network

import com.google.gson.annotations.SerializedName

data class RateDTO(
    @SerializedName("symbol") val symbol: String,
    @SerializedName("price") val price: Double
)