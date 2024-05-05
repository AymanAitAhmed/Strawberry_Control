package com.example.fraisecontrol.ui.mainScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.fraisecontrol.ui.theme.orangeLight


@Composable
fun CarouselBox(
    modifier: Modifier = Modifier,
    itemName: String,
    currentValueLabel: String,
    currentValue: String,
    targetValueLabel: String,
    targetValue: String,
    icon : Int
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(text = itemName, style = MaterialTheme.typography.headlineSmall)
                Row(verticalAlignment = Alignment.Bottom) {
                    Column {
                        Text(
                            text = currentValueLabel, style = MaterialTheme.typography.titleMedium

                        )
                        Text(
                            text = currentValue,
                            style = MaterialTheme.typography.headlineLarge
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = targetValueLabel, style = MaterialTheme.typography.bodySmall
                        )
                        Text(text = targetValue, style = MaterialTheme.typography.titleLarge)
                    }

                }
            }
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "",
                modifier = Modifier
                    .weight(0.5f),
                tint = orangeLight
            )

        }
    }
}

data class CarouselBoxItem(
    val itemName: String,
    val currentValueLabel: String,
    val currentValue: String,
    val targetValueLabel: String,
    val targetValue: String,
    val icon : Int
)