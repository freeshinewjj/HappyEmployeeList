package com.happygroup.employeelist.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class EmployeeType {
    @SerialName("FULL_TIME") FULL_TIME, @SerialName("PART_TIME") PART_TIME, @SerialName("CONTRACTOR") CONTRACTOR
}