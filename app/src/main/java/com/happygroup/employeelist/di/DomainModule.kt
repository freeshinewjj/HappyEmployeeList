package com.happygroup.employeelist.di

import com.happygroup.employeelist.domain.GetEmployeesWithRandomEndpointWithWeightUseCase
import com.happygroup.employeelist.data.repository.EmployeesRepository
import com.happygroup.employeelist.config.Config
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import dagger.hilt.migration.DisableInstallInCheck
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {
    @Provides
    fun provideEmployeesFromRandomEndpointWithWeight(repository: EmployeesRepository,
                                                     @Named("endpoints") endpoints: List<String>,
                                                     @Named("endpoint_weights") weights: List<Int>): GetEmployeesWithRandomEndpointWithWeightUseCase {
        return GetEmployeesWithRandomEndpointWithWeightUseCase(repository, endpoints, weights)
    }

    @Named("endpoints")
    @Provides
    fun provideEndPoints(config: Config): List<String> {
        return config.endpoints
    }

    @Named("endpoint_weights")
    @Provides
    fun provideEndpointWights(config: Config): List<Int> {
        return config.endpointsWeights
    }
}