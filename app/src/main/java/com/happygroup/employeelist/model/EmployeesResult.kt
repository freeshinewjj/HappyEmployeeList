package com.happygroup.employeelist.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EmployeesResult(
    @SerialName("employees")
    val employees: List<EmployeeInfo>
)
