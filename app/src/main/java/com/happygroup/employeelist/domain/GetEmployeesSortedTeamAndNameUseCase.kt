package com.happygroup.employeelist.domain

import com.happygroup.employeelist.model.EmployeeInfo
import javax.inject.Inject

// This use case sort the result obtained from GetEmployeesWithRandomEndpointWithWeightUseCase with employee team and full name
class GetEmployeesSortedTeamAndNameUseCase @Inject constructor(private val getEmployeesWithRandomEndpointWithWeight: GetEmployeesWithRandomEndpointWithWeightUseCase) {
    suspend operator fun invoke(): List<EmployeeInfo> {
        val employeeList = getEmployeesWithRandomEndpointWithWeight()
        return employeeList.sortedWith(compareBy({it.team}, {it.fullName}))
    }
}