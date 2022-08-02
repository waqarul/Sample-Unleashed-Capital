package com.wtmcodex.samplerates.data.repository

import com.wtmcodex.samplerates.data.base.Result
import com.wtmcodex.samplerates.model.rate.Rate

interface RateRepository {
    suspend fun getRates(): Result<List<Rate>?>
}