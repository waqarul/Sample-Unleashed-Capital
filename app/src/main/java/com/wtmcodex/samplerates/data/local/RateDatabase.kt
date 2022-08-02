package com.wtmcodex.samplerates.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.wtmcodex.samplerates.data.local.dao.RateDao
import com.wtmcodex.samplerates.data.local.entity.RateEntity

@Database(entities = [RateEntity::class], version = 1, exportSchema = false)
abstract class RateDatabase : RoomDatabase() {
    abstract fun rateDao(): RateDao
}