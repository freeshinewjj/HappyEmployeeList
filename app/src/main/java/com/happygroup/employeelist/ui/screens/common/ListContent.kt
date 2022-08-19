package com.happygroup.employeelist.ui.screens.common

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.happygroup.employeelist.R
import com.happygroup.employeelist.ui.screens.home.EmployeeItemUiState
import com.happygroup.employeelist.ui.theme.HappyEmployeeListTheme

@Composable
fun EmployeeList(items: List<EmployeeItemUiState>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(all = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = items,
            key = { employeeInfo ->
                employeeInfo.id
            }
        ) { employeeInfo ->
            EmployeeItem(employeeInfo)
        }
    }
}

@Composable
fun EmployeeItem(employeeInfo: EmployeeItemUiState) {
    Card(
        modifier = Modifier
            .height(80.dp)
            .fillMaxWidth(),
        elevation = 5.dp,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(20.dp)
        ){
            AsyncImage(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .size(64.dp),
                model = ImageRequest.Builder(LocalContext.current)
                    .data(employeeInfo.thumbnailPhotoUrl)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(R.drawable.ic_placeholder),
                error = painterResource(R.drawable.ic_placeholder),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = employeeInfo.fullName,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.subtitle1,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(text = employeeInfo.team, style = MaterialTheme.typography.subtitle2, maxLines = 1)
                Text(text = employeeInfo.email, style = MaterialTheme.typography.body2, maxLines = 1)
                Text(text = employeeInfo.phoneNumber, style = MaterialTheme.typography.body2, maxLines = 1)
            }
        }
    }
}


@Preview
@Composable
fun EmployeeListPreview() {
    HappyEmployeeListTheme {
        EmployeeList(items = listOf(
            EmployeeItemUiState("Emp1", "Jordan Smith", "https://s3.amazonaws.com/sq-mobile-interview/photos/16c00560-6dd3-4af4-97a6-d4754e7f2394/small.jpg",
                "core", "test@test.com", "12345678",
                "This is test one line biography."
            ),
            EmployeeItemUiState("Emp2", "Jordan Smith", "https://s3.amazonaws.com/sq-mobile-interview/photos/16c00560-6dd3-4af4-97a6-d4754e7f2394/small.jpg",
                "core", "test@test.com", "12345678",
                "This is test one line biography."
            ),
            EmployeeItemUiState("Emp3", "Jordan Smith", "https://s3.amazonaws.com/sq-mobile-interview/photos/16c00560-6dd3-4af4-97a6-d4754e7f2394/small.jpg",
                "core", "test@test.com", "12345678",
                "This is test one line biography."
            )
        )
        )
    }
}