package com.happygroup.employeelist

import com.happygroup.employeelist.domain.GetEmployeesSortedTeamAndNameUseCase
import com.happygroup.employeelist.model.EmployeeInfo
import com.happygroup.employeelist.model.EmployeeType
import com.happygroup.employeelist.ui.screens.home.EmployeeItemUiState
import com.happygroup.employeelist.ui.screens.home.HomeViewModel
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.*
import kotlinx.serialization.SerializationException
import org.junit.Test
import org.junit.Before
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.rules.TestWatcher
import org.junit.runner.Description

class HomeViewModelTest {
    @get:Rule
    val mainDispatcherRule = DispatcherTestRule()

    @MockK
    lateinit var sortedTeamAndNameUseCase: GetEmployeesSortedTeamAndNameUseCase

    @Before
    fun setUp() = MockKAnnotations.init(this)

    @Test
    fun testRefreshEmployees_Success() = runTest {
        val employeeList = listOf(
            EmployeeInfo(
            "emp1", "J Smith", "123456", "jsmith@test.com",
            "This is a one line biography", "http://www.happygroup.com/emp/emp1_small.jpg",
            "http://www.happygroup.com/emp/emp1_large.jpg", "Core", EmployeeType.FULL_TIME),
            EmployeeInfo(
                "emp2", "Q Smith", "1234567", "qsmith@test.com",
                "This is a one line biography as well", "http://www.happygroup.com/emp/emp2_small.jpg",
                "http://www.happygroup.com/emp/emp2_large.jpg", "Core", EmployeeType.PART_TIME))

        coEvery { sortedTeamAndNameUseCase.invoke() } returns  employeeList

        val viewModel = HomeViewModel(sortedTeamAndNameUseCase)
        viewModel.refreshEmployees()

        advanceUntilIdle()
        assertEquals(false, viewModel.employeeListUiState.isRefreshing)
        assertEquals(employeeList.map { EmployeeItemUiState(it.id, it.fullName, it.photoUrlSmall, it.team, it.emailAddress, it.phoneNumber, it.biography) }, viewModel.employeeListUiState.employees)
        assertEquals(false, viewModel.employeeListUiState.hasError)
    }

    @Test
    fun testRefreshEmployees_Empty() = runTest{
        val employeeList: List<EmployeeInfo> = listOf()
        coEvery { sortedTeamAndNameUseCase.invoke() } returns employeeList
        val viewModel = HomeViewModel(sortedTeamAndNameUseCase)
        viewModel.refreshEmployees()
        advanceUntilIdle()
        assertEquals(false, viewModel.employeeListUiState.isRefreshing)
        assertEquals(employeeList, viewModel.employeeListUiState.employees)
        assertEquals(false, viewModel.employeeListUiState.hasError)
    }

    @Test
    fun testRefreshEmployees_Malformed()  = runTest {
        val employeeList: () -> List<EmployeeInfo> = { throw SerializationException() }
        coEvery { sortedTeamAndNameUseCase.invoke() } answers { employeeList() }
        val viewModel = HomeViewModel(sortedTeamAndNameUseCase)
        viewModel.refreshEmployees()
        advanceUntilIdle()
        assertEquals(false, viewModel.employeeListUiState.isRefreshing)
        assertEquals(null, viewModel.employeeListUiState.employees)
        assertEquals(true, viewModel.employeeListUiState.hasError)
    }
}

class DispatcherTestRule(private val testDispatcher: TestDispatcher = StandardTestDispatcher()): TestWatcher() {
    override fun starting(description: Description) {
        Dispatchers.setMain(testDispatcher)
    }

    override fun finished(description: Description) {
        Dispatchers.resetMain()
    }
}