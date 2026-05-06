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
import coil.compose.AsyncImage
import create.develop.secondproj.data.loggin.remote.Address
import create.develop.secondproj.data.loggin.remote.Coordinates

@Composable
fun UserDetailScreen(
    firstName: String,
    gender: String,
    image: String,
    lastName: String,
    birthDate: String,
    bloodGroup: String,
    address: Address?,
    business: Address?,
    modifier: Modifier = Modifier,
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
                    text = "$firstName\t$lastName",
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

                    Text(
                        text = "Blood group:",
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = "Gender:",
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = "Birth date:",
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Address:",
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = "Business:",
                        fontWeight = FontWeight.Bold
                    )
                }
//                Spacer(Modifier.width(4.dp))
                Column(
                    modifier = Modifier
                        .background(Color(0x42EAECEA))
                        .padding(top = 12.dp, start = 4.dp, end = 12.dp)
                ) {
                    Text(text = bloodGroup)
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(text = gender)
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(text = birthDate)
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = address?.address + ", "
                            + address?.city + ", "
                            + address?.state + ", "
                            + address?.country
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(
                        text = business?.address ?: "No business information available"
                    )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, start = 2.dp, end = 24.dp)

            ) {
                AsyncImage(
                    model = image,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .height(120.dp)
                        .width(140.dp)
                )
            }
        }

    }

}

@Preview(showBackground = true)
@Composable
fun UserDetailScreenPreview() {
    UserDetailScreen(
        firstName = "First Name",
        gender = "Male",
        image = "https://randomuser.me/api/portraits/men/75.jpg",
        lastName = "Last Name",
        birthDate = "1990-01-01",
        bloodGroup = "A+",
        address = Address(
            address = "456 Broadway",
            city = "City",
            coordinates = Coordinates(
                lat = 40.7128,
                lng = -74.0060
            ),
            postalCode = "12345",
            state = "State",
            stateCode = "",
            country = "U.S"
        ),

        business = Address(
            address = "123 Main St",
            city = "City",
            coordinates = Coordinates(
                lat = 40.7128,
                lng = -74.0060
            ),
            postalCode = "12345",
            state = "State",
            stateCode = "",
            country = "Country"
        )

    )
}
