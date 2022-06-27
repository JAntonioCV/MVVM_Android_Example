package com.jantonioc.mvvmandroid.di

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import com.jantonioc.mvvmandroid.data.database.QuoteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    private const val QUOTEDATABASENAME= "quote_database"

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) = Room.databaseBuilder(context,QuoteDatabase::class.java, QUOTEDATABASENAME).build()

    @Singleton
    @Provides
    fun providerQuoteDao(db:QuoteDatabase) = db.getQuoteDao()


}