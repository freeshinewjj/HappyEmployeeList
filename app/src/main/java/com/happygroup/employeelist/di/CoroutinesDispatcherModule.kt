package com.happygroup.employeelist.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object CoroutinesDispatcherModule {
    @Provides
    @IoDispatcher
    fun provideIoDispatcher() = Dispatchers.IO
}