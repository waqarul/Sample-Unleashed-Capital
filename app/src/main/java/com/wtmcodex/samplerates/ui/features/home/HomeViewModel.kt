package com.wtmcodex.samplerates.ui.features.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wtmcodex.samplerates.data.base.Result
import com.wtmcodex.samplerates.data.base.ScreenState
import com.wtmcodex.samplerates.di.scopes.IoDispatcher
import com.wtmcodex.samplerates.domain.GetRateUseCase
import com.wtmcodex.samplerates.extensions.launchPeriodicAsync
import com.wtmcodex.samplerates.model.rate.Rate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val rateUseCase: GetRateUseCase,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) :
    ViewModel() {
    private val TAG = this::class.simpleName

    private val _viewModelState = MutableStateFlow(HomeScreenState.Empty)
    private lateinit var job: Job
    val uiState = _viewModelState
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            HomeScreenState.Empty
        )

    init {
        job = CoroutineScope(Dispatchers.IO)
            .launchPeriodicAsync(TimeUnit.SECONDS.toMillis(10)) {
                getRates()
            }
    }

    fun getRates() {
        viewModelScope.launch(ioDispatcher) {
            rateUseCase(Unit)
                .handle(
                    onSuccess = { onRatesFetchSuccess(it) },
                    onError = { onRatesFailedSuccess(it.localizedMessage) }
                )
        }
    }

    private fun onRatesFetchSuccess(result: Result<List<Rate>?>) {
        when (result) {
            is Result.Success -> {
                if (result.data != null) {
                    handleRates(result.data)
                } else {
                    onRatesFailedSuccess("Please try again.")
                }
            }
            is Result.Error -> {
                onRatesFailedSuccess(result.exception.localizedMessage)
            }
        }
    }

    private fun handleRates(rates: List<Rate>) {
        Log.e(TAG, rates.toString())
        Log.e(TAG, "" + rates.hashCode())

        val oldList = _viewModelState.value.rates
        val tempPrices = rates.map { price ->
            val oldItem = oldList?.firstOrNull { it.symbol == price.symbol } ?: price
            price.isIncreased = price.price >= oldItem.price
            price
        }

        _viewModelState.update {
            it.copy(
                screenState = ScreenState.Success,
                rates = tempPrices,
                error = null
            )
        }
    }

    private fun onRatesFailedSuccess(error: String?) {
        _viewModelState.update {
            it.copy(
                screenState = ScreenState.Error,
                rates = null,
                error = error
            )
        }
    }

    override fun onCleared() {
        if (::job.isInitialized) {
            job.cancel()
        }
        super.onCleared()
    }
}