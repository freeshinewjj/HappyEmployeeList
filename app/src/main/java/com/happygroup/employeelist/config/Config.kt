package com.happygroup.employeelist.config

// Represents the configuration items included
data class Config(
    val endpointBaseUrl: String,
    val defaultEndpoint: String,
    val badListEndpoint: String,
    val emptyListEndpoint: String,
    val endpointsWeights: List<Int> = listOf(3,1,1)
) {
    val endpoints: List<String> by lazy {
        listOf(defaultEndpoint, badListEndpoint, emptyListEndpoint)
    }
}