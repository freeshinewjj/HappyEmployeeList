package com.happygroup.employeelist.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EmployeeInfo(
    @SerialName("uuid")
    val id: String,
    @SerialName("full_name")
    val fullName: String,
    @SerialName("phone_number")
    val phoneNumber: String,
    @SerialName("email_address")
    val emailAddress: String,
    @SerialName("biography")
    val biography: String,
    @SerialName("photo_url_small")
    val photoUrlSmall: String,
    @SerialName("photo_url_large")
    val photoUrlLarge:String,
    @SerialName("team")
    val team: String,
    @SerialName("employee_type")
    val employeeType: EmployeeType
)
