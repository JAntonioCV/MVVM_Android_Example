package com.jantonioc.mvvmandroid.domain

import com.jantonioc.mvvmandroid.data.QuoteRepository
import com.jantonioc.mvvmandroid.data.model.QuoteModel
import com.jantonioc.mvvmandroid.domain.model.Quote
import javax.inject.Inject

class GetRandomQuoteUseCase @Inject constructor(
    private val repository: QuoteRepository
) {

    suspend operator fun invoke():Quote?{
        val quotes = repository.getAllQuotesFromDatabase()
        if(!quotes.isNullOrEmpty())
        {
            val randomNumber = (quotes.indices).random()
            return quotes[randomNumber]
        }
        return null
    }
}