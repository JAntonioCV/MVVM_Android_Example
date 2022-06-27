package com.jantonioc.mvvmandroid.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jantonioc.mvvmandroid.domain.GetQuotesUseCase
import com.jantonioc.mvvmandroid.domain.GetRandomQuoteUseCase
import com.jantonioc.mvvmandroid.domain.model.Quote
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(
    private val getQuotesUseCase: GetQuotesUseCase,
    private val getQuoteRandomUseCase: GetRandomQuoteUseCase
): ViewModel() {

    val quoteModel = MutableLiveData<Quote>()
    val isLoading = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getQuotesUseCase()
            if (!result.isNullOrEmpty())
            {
                isLoading.postValue(false)
                quoteModel.postValue(result[0])

            }
        }
    }
    fun randomQuote(){
        viewModelScope.launch {
            isLoading.postValue(true)
            val result  = getQuoteRandomUseCase()
            if(result!=null)
            {
                isLoading.postValue(false)
                quoteModel.postValue(result)
            }
            isLoading.postValue(false)
        }
    }




}