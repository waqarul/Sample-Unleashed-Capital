package com.wtmcodex.samplerates.data.remote

import com.wtmcodex.samplerates.data.base.Result
import com.wtmcodex.samplerates.model.network.RateDTO

interface RateRemoteDataSource {
    suspend fun getRates(): Result<List<RateDTO>?>
}