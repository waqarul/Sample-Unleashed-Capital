package com.wtmcodex.samplerates.data.local.dao

import androidx.room.*
import com.wtmcodex.samplerates.constants.DatabaseConstants
import com.wtmcodex.samplerates.data.local.entity.RateEntity

@Dao
interface RateDao : BaseDao<RateEntity> {
    @Insert
    suspend fun insertRate(rate: RateEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateRate(rate: RateEntity)

    @Query(DatabaseConstants.QUERY_SELECT_RATES)
    suspend fun getAllRates(): List<RateEntity>?

    @Query(DatabaseConstants.QUERY_SELECT_RATES_BY_SYMBOL)
    suspend fun getRateBySymbol(symbol: String): RateEntity?

    @Query(DatabaseConstants.QUERY_DELETE_RATES)
    suspend fun deleteAllRates()
}