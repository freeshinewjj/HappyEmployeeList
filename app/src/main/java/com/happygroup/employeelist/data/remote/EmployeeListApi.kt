package com.happygroup.employeelist.data.remote

import com.happygroup.employeelist.model.EmployeesResult
import retrofit2.http.GET
import retrofit2.http.Path

interface EmployeeListApi {
    @GET("sq-mobile-interview/{endpoint}")
    suspend fun getAllEmployees(@Path("endpoint", encoded = false) endpoint: String): EmployeesResult
}