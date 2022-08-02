package com.wtmcodex.samplerates.model.rate

data class Rate(
    val symbol: String,
    val price: Double
) {
    var isIncreased = false
}
