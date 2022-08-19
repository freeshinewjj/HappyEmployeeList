package com.happygroup.employeelist.ui.screens.home

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.happygroup.employeelist.ui.theme.HappyEmployeeListTheme
import com.happygroup.employeelist.ui.theme.topAppBarBackgroundColor
import com.happygroup.employeelist.ui.theme.topAppBarContentColor

@Composable
fun HomeTopBar(
    title: String,
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                color = MaterialTheme.colors.topAppBarContentColor
            ) },
        backgroundColor = MaterialTheme.colors.topAppBarBackgroundColor
    )
}

@Composable
@Preview
fun HomeTopBarPreview() {
    HappyEmployeeListTheme {
        HomeTopBar("Happy Employees")
    }
}