package com.wtmcodex.samplerates.constants

interface DatabaseConstants {
    companion object {
        const val DATABASE_NAME = "RatesDatabase.db"
        const val TABLE_RATES = "Rates"
        const val QUERY_SELECT_RATES = "SELECT * FROM $TABLE_RATES"
        const val QUERY_SELECT_RATES_BY_SYMBOL = "SELECT * FROM $TABLE_RATES WHERE symbol=:symbol "
        const val QUERY_DELETE_RATES = "DELETE FROM $TABLE_RATES"
    }
}