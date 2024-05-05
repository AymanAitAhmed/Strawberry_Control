package com.example.fraisecontrol.ui.mainScreen

import CustomCircularProgressIndicator
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.fraisecontrol.ui.theme.orangeLight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    tempCurrentValue: Int,
    tempTargetValue: Int,
    humCurrentValue: Int,
    humTargetValue: Int,
    lightCurrentValue: Int,
    lightTargetValue: Int,
    valueSymbol: String,
    indicatorValue: Int,
    maxValue: Int,
    minValue: Int,
    onSwipe: (Int) -> Unit,
    onTargetValueIncrease: () -> Unit,
    onTargetValueDecrease: () -> Unit,
) {
    Scaffold(
        topBar = {
            MediumTopAppBar(
                title = {
                    Text(text = "Dashboard", style = MaterialTheme.typography.headlineLarge.copy(fontWeight = FontWeight.Bold))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = orangeLight,
                    titleContentColor = Color.White
                )
            )
        }
    ) { paddingValues ->

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(paddingValues)
        ) {
            Carousel(
                modifier = Modifier.fillMaxHeight(0.3f),
                tempCurrentValue = tempCurrentValue,
                tempTargetValue = tempTargetValue,
                humCurrentValue = humCurrentValue,
                humTargetValue = humTargetValue,
                lightCurrentValue = lightCurrentValue,
                lightTargetValue = lightTargetValue,
                onSwipe = onSwipe
            )

            CustomCircularProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth(0.6f)
                    .aspectRatio(1f),
                currentValue = indicatorValue,
                maxValue = maxValue,
                minValue = minValue,
                primaryColor = orangeLight,
                secondaryColor = Color.LightGray,
                valueSymbol = valueSymbol
            )
            Spacer(modifier = Modifier.fillMaxHeight(0.2f))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxHeight(0.3f)
            ) {
                Button(
                    onClick = onTargetValueIncrease,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = orangeLight,
                        contentColor = Color.White
                    ),
                    modifier = Modifier.fillMaxHeight(),
                    shape = RoundedCornerShape(22.dp)
                ) {
                    Icon(imageVector = Icons.Default.KeyboardArrowUp, contentDescription = "")
                }
                Button(
                    onClick = onTargetValueDecrease,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = orangeLight,
                        contentColor = Color.White
                    ),
                    modifier = Modifier.fillMaxHeight(),
                    shape = RoundedCornerShape(22.dp)

                ) {
                    Icon(imageVector = Icons.Default.KeyboardArrowDown, contentDescription = "")
                }
            }
        }
    }
}