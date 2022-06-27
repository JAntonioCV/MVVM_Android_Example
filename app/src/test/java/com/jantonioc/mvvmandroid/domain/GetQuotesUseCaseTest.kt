package com.jantonioc.mvvmandroid.domain

import com.jantonioc.mvvmandroid.data.QuoteRepository
import com.jantonioc.mvvmandroid.domain.model.Quote
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.verify
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


class GetQuotesUseCaseTest{

    @RelaxedMockK
    private lateinit var quoteRepository: QuoteRepository

    lateinit var getQuotesUseCase: GetQuotesUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        getQuotesUseCase = GetQuotesUseCase(quoteRepository)
    }

    @Test
    fun whenTheApiDoesntReturnAnythingThenGetValuesFromDatabase() = runBlocking {
        //Given
        coEvery { quoteRepository.getAllQuotesFromApi() } returns emptyList()
        //When
        getQuotesUseCase()
        //Then
        coVerify(exactly = 1) {
            quoteRepository.getAllQuotesFromDatabase()
        }
    }

    @Test
    fun whenTheApiReturnSomethingThenGetValuesFromApi() = runBlocking {
        //given
        val mylist = listOf(Quote("Dejame un Comentario","JAntonioC"))
        coEvery { quoteRepository.getAllQuotesFromApi() } returns mylist
        //when
        val response = getQuotesUseCase()
        //then
        coVerify(exactly = 1) { quoteRepository.clearQuotes() }
        coVerify(exactly = 1) { quoteRepository.insertQuotes(any()) }
        coVerify(exactly = 0) { quoteRepository.getAllQuotesFromDatabase() }
        assert(mylist == response)

    }
}