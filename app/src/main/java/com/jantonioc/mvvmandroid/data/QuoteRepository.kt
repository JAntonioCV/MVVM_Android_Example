package com.jantonioc.mvvmandroid.data

import com.jantonioc.mvvmandroid.data.database.dao.QuoteDao
import com.jantonioc.mvvmandroid.data.database.entities.QuoteEntitty
import com.jantonioc.mvvmandroid.data.model.QuoteModel
import com.jantonioc.mvvmandroid.data.network.QuoteService
import com.jantonioc.mvvmandroid.domain.model.Quote
import com.jantonioc.mvvmandroid.domain.model.toDomain
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val api: QuoteService,
    private val quoteDao: QuoteDao
) {

    suspend fun getAllQuotesFromApi():List<Quote>{
        val response: List<QuoteModel> = api.getQuotes()
        return response.map { it.toDomain() }
    }

    suspend fun getAllQuotesFromDatabase():List<Quote>{
        val response = quoteDao.getAllQuotes()
        return response.map { it.toDomain() }
    }

    suspend fun insertQuotes(quotes:List<QuoteEntitty>){
        quoteDao.insertAll(quotes)

    }

    suspend fun clearQuotes() {
        quoteDao.deleteAllQuotes()
    }


}