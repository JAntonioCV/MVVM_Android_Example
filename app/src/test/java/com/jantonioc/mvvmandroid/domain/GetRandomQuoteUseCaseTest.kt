package com.jantonioc.mvvmandroid.domain

import com.jantonioc.mvvmandroid.data.QuoteRepository
import com.jantonioc.mvvmandroid.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class GetRandomQuoteUseCaseTest{
    @RelaxedMockK
    private lateinit var quoteRepository: QuoteRepository

    lateinit var getRandomQuoteUseCase: GetRandomQuoteUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getRandomQuoteUseCase = GetRandomQuoteUseCase(quoteRepository)
    }

    @Test
    fun whenTheDatabaseIsEmptyReturnNull() = runBlocking {
        //Given
        coEvery { quoteRepository.getAllQuotesFromDatabase() } returns emptyList()
        //When
        val response = getRandomQuoteUseCase()
        //Then
        assert(response==null)
    }

    @Test
    fun whenTheDatabaseIsNotEmptyReturnQuote() = runBlocking {
        //Given
        val quoteList = listOf(Quote("Holi", "AristiDevs"))
        coEvery { quoteRepository.getAllQuotesFromDatabase() } returns quoteList
        //When
        val response = getRandomQuoteUseCase()
        //Then
        assert(response==quoteList.first())
    }



}