package com.wtmcodex.samplerates.data.remote.retrofit

import com.wtmcodex.samplerates.constants.APIConstants
import com.wtmcodex.samplerates.model.network.BaseDTO
import com.wtmcodex.samplerates.model.network.RateDTO
import retrofit2.Response
import retrofit2.http.GET

interface RateApiService {
    @GET(APIConstants.GET_RATES)
    suspend fun getRates(): Response<BaseDTO<List<RateDTO>>>
}