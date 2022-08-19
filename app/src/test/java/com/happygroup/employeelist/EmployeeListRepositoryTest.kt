package com.happygroup.employeelist

import com.happygroup.employeelist.data.remote.EmployeeListApi
import com.happygroup.employeelist.di.defaultConfig
import com.happygroup.employeelist.model.EmployeeInfo
import com.happygroup.employeelist.model.EmployeeType
import com.happygroup.employeelist.model.EmployeesResult
import com.happygroup.employeelist.data.repository.EmployeesRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

class EmployeeListRepositoryTest {
    @MockK
    lateinit var employeeListApi: EmployeeListApi

    @Before
    fun setUp() = MockKAnnotations.init(this)

    @Test
    fun testGetEmployeeListApi() = runTest {
        val employeeList = listOf(
            EmployeeInfo(
                "emp1", "J Smith", "123456", "jsmith@test.com",
                "This is a one line biography", "http://www.happygroup.com/emp/emp1_small.jpg",
                "http://www.happygroup.com/emp/emp1_large.jpg", "Core", EmployeeType.FULL_TIME),
            EmployeeInfo(
                "emp2", "Q Smith", "1234567", "qsmith@test.com",
                "This is a one line biography as well", "http://www.happygroup.com/emp/emp2_small.jpg",
                "http://www.happygroup.com/emp/emp2_large.jpg", "Core", EmployeeType.PART_TIME)
        )
        val employeesResult = EmployeesResult(employeeList)
        coEvery { employeeListApi.getAllEmployees(any()) } returns employeesResult

        val testDispatcher = StandardTestDispatcher(testScheduler)

        val repository = EmployeesRepository(employeeListApi, testDispatcher)
        val allEmployees = repository.getAllEmployees(defaultConfig.defaultEndpoint)
        advanceUntilIdle()
        assertEquals(employeesResult.employees, allEmployees)
    }
}