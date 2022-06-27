package com.jantonioc.mvvmandroid.data.network

import com.jantonioc.mvvmandroid.data.model.QuoteModel
import retrofit2.Response
import retrofit2.http.GET

interface QuoteApiClient {
    @GET("/.json")
    suspend fun getAllQuotes():Response<List<QuoteModel>>
}