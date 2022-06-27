package com.jantonioc.mvvmandroid.domain

import com.jantonioc.mvvmandroid.data.QuoteRepository
import com.jantonioc.mvvmandroid.data.database.entities.toDatabase
import com.jantonioc.mvvmandroid.data.model.QuoteModel
import com.jantonioc.mvvmandroid.domain.model.Quote
import javax.inject.Inject


class GetQuotesUseCase @Inject constructor(
    private val repository: QuoteRepository
) {

    suspend operator fun invoke():List<Quote>?{
        val quotes =  repository.getAllQuotesFromApi()

        return if (quotes.isNotEmpty()) {
            repository.clearQuotes()
            repository.insertQuotes(quotes.map { it.toDatabase() })
            quotes
        }else {
            repository.getAllQuotesFromDatabase()
        }
    }


}