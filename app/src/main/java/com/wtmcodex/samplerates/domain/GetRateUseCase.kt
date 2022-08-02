package com.wtmcodex.samplerates.domain

import com.wtmcodex.samplerates.data.base.Result
import com.wtmcodex.samplerates.data.base.UseCase
import com.wtmcodex.samplerates.data.repository.RateRepository
import com.wtmcodex.samplerates.di.scopes.IoDispatcher
import com.wtmcodex.samplerates.model.rate.Rate
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetRateUseCase @Inject constructor(
    private val repository: RateRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) :
    UseCase<Unit, Result<List<Rate>?>>(ioDispatcher) {

    override suspend fun execute(parameters: Unit): Result<List<Rate>?> {
        return repository.getRates()
    }
}