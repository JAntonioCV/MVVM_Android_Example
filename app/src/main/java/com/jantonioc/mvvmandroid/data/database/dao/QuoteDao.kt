package com.jantonioc.mvvmandroid.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jantonioc.mvvmandroid.data.database.entities.QuoteEntitty

@Dao
interface QuoteDao {

    @Query("SELECT * FROM quote_table ORDER BY author DESC")
    suspend fun getAllQuotes():List<QuoteEntitty>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(quotes:List<QuoteEntitty>)

    @Query("DELETE FROM quote_table")
    suspend fun deleteAllQuotes()

}