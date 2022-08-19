package com.happygroup.employeelist.ui.screens.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.happygroup.employeelist.R
import com.happygroup.employeelist.ui.theme.HappyEmployeeListTheme
import com.happygroup.employeelist.ui.theme.noEmployeesWarn

@Composable
fun NoEmployees(message: String, color: Color) {
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Image(painter = painterResource(R.drawable.bg_empty_office), contentDescription = "Empty Office Background")
        Box(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)) {
            Text(text = message, style = MaterialTheme.typography.body2, color = color)
        }
    }
}

@Composable
@Preview(showBackground = true)
fun NoEmployeesPreview() {
    HappyEmployeeListTheme {
        NoEmployees(
            message = "You are now entering an empty office. You have the magic to pull to refresh the employees...",
            color = MaterialTheme.colors.noEmployeesWarn
        )
    }
}