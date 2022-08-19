package com.happygroup.employeelist.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.happygroup.employeelist.R
import com.happygroup.employeelist.ui.screens.common.EmployeeList
import com.happygroup.employeelist.ui.theme.noEmployeesWarn

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    // use collectAsStateWithLifecycle to collect flow in a lifecycle aware manner
    val employeeListUiState = viewModel.employeeListUiState

    var topBarTitle = stringResource(id = R.string.home_top_bar_title_success)
    if (employeeListUiState.hasError) {
        topBarTitle = stringResource(id = R.string.home_top_bar_title_error)
    } else {
        if (employeeListUiState.employees?.isEmpty() == true) {
            topBarTitle = stringResource(id = R.string.home_top_bar_title_empty)
        }
    }

    Scaffold(
        topBar = {
            HomeTopBar(title = topBarTitle)
        }
    ) {
        val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = employeeListUiState.isRefreshing)
        SwipeRefresh(state = swipeRefreshState, onRefresh = { viewModel.refreshEmployees() }) {
            Box(modifier = Modifier
                .fillMaxSize()
            ) {
                // In a fresh state, need to do a refresh
                if (employeeListUiState == EmployeeListUiState()) {
                    viewModel.refreshEmployees()
                } else {
                    if (employeeListUiState.employees.isNullOrEmpty()) {
                        // This means employee list is empty
                        if (employeeListUiState.hasError) {
                            // there is error in fetching employee list
                            NoEmployees(message = stringResource(id = R.string.fetch_employee_list_error), color = MaterialTheme.colors.error)
                        } else if (employeeListUiState.employees?.isEmpty() == true) {
                            NoEmployees(message = stringResource(id = R.string.empty_office), color = MaterialTheme.colors.noEmployeesWarn)
                        }
                    } else {
                        EmployeeList(items = employeeListUiState.employees)
                    }
                }
            }
        }
    }
}
