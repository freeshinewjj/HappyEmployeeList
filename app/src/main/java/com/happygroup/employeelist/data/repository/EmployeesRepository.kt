package com.happygroup.employeelist.data.repository

import com.happygroup.employeelist.data.remote.EmployeeListApi
import com.happygroup.employeelist.di.IoDispatcher
import com.happygroup.employeelist.model.EmployeeInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

/**
Currently, the only data source is the network data which is represented and obtained through EmployeeListApi.
*/
@Singleton
class EmployeesRepository @Inject constructor(
    private val employeeListApi: EmployeeListApi,
    @IoDispatcher
    private val ioDispatcher: CoroutineDispatcher
) {
    suspend fun getAllEmployees(
        endpoint: String
    ): List<EmployeeInfo> = withContext(ioDispatcher) {
            employeeListApi.getAllEmployees(endpoint).employees
        }
}