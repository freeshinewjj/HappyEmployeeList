package com.happygroup.employeelist.di

import com.happygroup.employeelist.config.Config
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ConfigModule {
    @Provides
    @Singleton
    fun provideConfig(): Config {
        return defaultConfig
    }
}

val defaultConfig = Config(endpointBaseUrl = "https://s3.amazonaws.com/",
    defaultEndpoint = "employees.json",
    badListEndpoint = "employees_malformed.json",
    emptyListEndpoint = "employees_empty.json",
    endpointsWeights = listOf(2,1,1))