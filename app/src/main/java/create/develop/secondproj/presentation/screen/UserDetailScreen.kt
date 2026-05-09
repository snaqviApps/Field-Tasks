package create.develop.secondproj.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import create.develop.secondproj.data.loggin.remote.logindetailsdata.Address
import create.develop.secondproj.data.loggin.remote.logindetailsdata.Coordinates
import create.develop.secondproj.data.loggin.remote.UserUIDetails
import create.develop.secondproj.presentation.UserInputViewModel

@Composable
fun UserDetailScreen(
    modifier: Modifier = Modifier,
    userDetail: UserUIDetails,
    viewModel: UserInputViewModel = viewModel()
) {
    Card(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 24.dp, start = 16.dp, end = 16.dp, bottom = 18.dp)
            .background(Color(0x42EAECEA)),
        elevation = CardDefaults.elevatedCardElevation(12.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(top = 32.dp, start = 8.dp, end = 4.dp)
                .background(Color(0x42EAECEA))
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "${userDetail.firstName}\t${userDetail.lastName}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(Modifier.height(12.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier
                        .background(Color(0x42EAECEA))
                        .padding(top = 12.dp, start = 2.dp, end = 12.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 4.dp, end = 4.dp)
                    ) {
                        Text(
                            text = "Blood group:\t\t\t${userDetail.bloodGroup}",
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(Modifier.height(6.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 4.dp, end = 4.dp)
                    ) {
                        Text(
                            text = "Gender:\t\t\t\t\t\t\t${userDetail.gender}",
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(Modifier.height(6.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 4.dp, end = 4.dp)
                    ) {
                        Text(
                            text = "Birth date:\t\t\t\t\t${userDetail.birthDate}",
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 4.dp, end = 4.dp, top = 12.dp)
                    ) {
                        // Improved Address formatting to avoid "null" strings
                        val addressText = if (userDetail.address != null) {
                            listOfNotNull(
                                userDetail.address.address,
                                userDetail.address.city,
                                userDetail.address.state,
                                userDetail.address.country
                            )
                                .filter { it.isNotBlank() }
                                .joinToString(", ")
                        } else "N/A"
                        Text(text = "Address:\t\t\t\t\t$addressText", fontWeight = FontWeight.Bold)
                    }
                    Spacer(Modifier.height(12.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 4.dp, end = 4.dp)
                    ) {
                        // Improved Business Address formatting
                        val businessText = if (userDetail.business != null) {
                            listOfNotNull(
                                userDetail.business.address,
                                userDetail.business.city,
                                userDetail.business.state,
                                userDetail.business.country
                            )
                                .filter { it.isNotBlank() }
                                .joinToString(", ")
                        } else "No business information available"
                        Text(text = "Business:\t\t\t\t\t$businessText")
                    }
                    Spacer(Modifier.height(12.dp))
                    Button(
                        onClick = {
                            viewModel.testRefresh()
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = CardDefaults.elevatedShape,
                        ) {
                        Text(text = "Test Refresh Token")
                    }
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp, start = 2.dp, end = 24.dp)
        ) {
            AsyncImage(
                model = userDetail.image,
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 12.dp)
                    .height(120.dp)
                    .width(140.dp)
            )
        }
    }

}

@Preview(showBackground = true)
@Composable
fun UserDetailScreenPreview() {
    UserDetailScreen(
        userDetail = UserUIDetails(
            firstName = "John",
            lastName = "Doe",
            gender = "Male",
            image = "https://example.com/image.jpg",
            birthDate = "1990-01-01",
            bloodGroup = "A+",

            email = "john.c.breckinridge@altostrat.com",
            address = Address(
                address = "123 Main St, Apt # 1310",
                city = "Cityville",
                state = "State",
                country = "Country",
                postalCode = "12345",
                coordinates = Coordinates(lat = 40.7128, lng = -74.0060),
                stateCode = "ST"
            ),
            business = Address(
                address = "456 Business St",
                city = "Businessville",
                state = "Business",
                country = "Businessland",
                postalCode = "54321",
                coordinates = Coordinates(lat = 37.7749, lng = -122.4194),
                stateCode = "BS"
            )
        )
    )
}
