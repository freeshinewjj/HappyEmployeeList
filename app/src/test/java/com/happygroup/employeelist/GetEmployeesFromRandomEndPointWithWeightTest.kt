package com.happygroup.employeelist

import com.happygroup.employeelist.di.defaultConfig
import com.happygroup.employeelist.domain.GetEmployeesWithRandomEndpointWithWeightUseCase
import com.happygroup.employeelist.model.EmployeeInfo
import com.happygroup.employeelist.model.EmployeeType
import com.happygroup.employeelist.data.repository.EmployeesRepository
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

class GetEmployeesFromRandomEndPointWithWeightTest {
    @MockK
    lateinit var employeesRepository: EmployeesRepository


    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun testGetEmployeesFromRandomEndpoint() = runTest {
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
        val endpointIdx = 0

        coEvery { employeesRepository.getAllEmployees(defaultConfig.endpoints[endpointIdx]) } returns employeeList
        val getEmployeesWithRandomEndpointWithWeightUseCase = GetEmployeesWithRandomEndpointWithWeightUseCase(employeesRepository, defaultConfig.endpoints, listOf(1,0,0))

        assertEquals(employeeList, getEmployeesWithRandomEndpointWithWeightUseCase())
    }
}