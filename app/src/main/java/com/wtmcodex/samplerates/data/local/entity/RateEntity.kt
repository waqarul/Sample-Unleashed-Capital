package com.wtmcodex.samplerates.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wtmcodex.samplerates.constants.DatabaseConstants

@Entity(tableName = DatabaseConstants.TABLE_RATES)
class RateEntity(
    @PrimaryKey
    @ColumnInfo(name = "symbol") val symbol: String,
    @ColumnInfo(name = "price") val price: String
)