package com.happygroup.employeelist.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.happygroup.employeelist.domain.GetEmployeesSortedTeamAndNameUseCase
import com.happygroup.employeelist.model.EmployeeInfo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.serialization.SerializationException
import retrofit2.HttpException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val sortedTeamAndNameUseCase: GetEmployeesSortedTeamAndNameUseCase
): ViewModel() {
    private var fetchEmployeeListJob: Job? = null

    var employeeListUiState by mutableStateOf(EmployeeListUiState())
        private set

    fun refreshEmployees() {
        fetchEmployeeListJob?.cancel()
        fetchEmployeeListJob = viewModelScope.launch {
            employeeListUiState = employeeListUiState.copy(isRefreshing = true)
            employeeListUiState = try {
                val employeesList = sortedTeamAndNameUseCase().map { EmployeeItemUiState(it.id, it.fullName, it.photoUrlSmall, it.team, it.emailAddress, it.phoneNumber, it.biography) }
                employeeListUiState.copy(isRefreshing = false, employees = employeesList, hasError = false)
            } catch (exception: Exception) {
                when(exception) {
                    is HttpException, is SerializationException -> {
                        employeeListUiState.copy(isRefreshing = false, employees = null, hasError = true)
                    }
                    else -> throw  exception
                }
            }
        }
    }
}

data class EmployeeListUiState(
    val isRefreshing: Boolean = false,
    val employees: List<EmployeeItemUiState>? = null,
    val hasError: Boolean = false
)

data class EmployeeItemUiState (
    val id: String,
    val fullName: String,
    val thumbnailPhotoUrl: String,
    val team: String,
    val email: String,
    val phoneNumber: String,
    val biography: String
)