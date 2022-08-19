package com.happygroup.employeelist

import com.happygroup.employeelist.domain.GetEmployeesSortedTeamAndNameUseCase
import com.happygroup.employeelist.domain.GetEmployeesWithRandomEndpointWithWeightUseCase
import com.happygroup.employeelist.model.EmployeeInfo
import com.happygroup.employeelist.model.EmployeeType
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class GetEmployeesSortedUseCaseTest {
    @MockK
    lateinit var getEmployeesWithRandomEndpointWithWeight: GetEmployeesWithRandomEndpointWithWeightUseCase

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
    }

    @Test
    fun testGetEmployeesSorted() = runTest {
        val unsortedList = listOf(
            EmployeeInfo("emp1", "J Smith", "123456", "jsmith@test.com",
            "This is a one line biography", "http://www.happygroup.com/emp/emp1_small.jpg",
            "http://www.happygroup.com/emp/emp1_large.jpg", "Sale", EmployeeType.FULL_TIME),
            EmployeeInfo("emp2", "Q Smith", "1234567", "qsmith@test.com",
            "This is a one line biography", "http://www.happygroup.com/emp/emp2_small.jpg",
            "http://www.happygroup.com/emp/emp2_large.jpg", "Core", EmployeeType.FULL_TIME),
            EmployeeInfo("emp2", "A Smith", "1234567", "qsmith@test.com",
                "This is a one line biography", "http://www.happygroup.com/emp/emp2_small.jpg",
                "http://www.happygroup.com/emp/emp2_large.jpg", "Sale", EmployeeType.FULL_TIME)
        )
        val sortedList = listOf(unsortedList[1], unsortedList[2], unsortedList[0])
        coEvery { getEmployeesWithRandomEndpointWithWeight.invoke() } returns unsortedList

        val getEmployeesSortedTeamAndNameUseCase = GetEmployeesSortedTeamAndNameUseCase(getEmployeesWithRandomEndpointWithWeight)
        assertEquals(sortedList, getEmployeesSortedTeamAndNameUseCase())
    }
}