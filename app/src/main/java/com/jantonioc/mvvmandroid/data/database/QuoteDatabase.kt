package com.jantonioc.mvvmandroid.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jantonioc.mvvmandroid.data.database.dao.QuoteDao
import com.jantonioc.mvvmandroid.data.database.entities.QuoteEntitty

@Database(entities = [QuoteEntitty::class], version = 1)
abstract class QuoteDatabase: RoomDatabase() {

    abstract fun getQuoteDao():QuoteDao

}