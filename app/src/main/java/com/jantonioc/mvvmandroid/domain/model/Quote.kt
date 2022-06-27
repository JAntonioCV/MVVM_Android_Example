package com.jantonioc.mvvmandroid.domain.model

import com.jantonioc.mvvmandroid.data.database.entities.QuoteEntitty
import com.jantonioc.mvvmandroid.data.model.QuoteModel

data class Quote(val quote:String, val author: String)

fun QuoteModel.toDomain() = Quote(quote, author)
fun QuoteEntitty.toDomain() = Quote(quote, author)