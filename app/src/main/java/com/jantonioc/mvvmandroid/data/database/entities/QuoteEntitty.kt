package com.jantonioc.mvvmandroid.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jantonioc.mvvmandroid.domain.model.Quote

@Entity(tableName = "quote_table")
data class QuoteEntitty(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name="id") val id: Int = 0,
    @ColumnInfo(name="quote") val quote: String,
    @ColumnInfo(name="author") val author: String)

fun Quote.toDatabase() = QuoteEntitty(quote = quote,author = author)
