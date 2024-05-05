package com.example.fraisecontrol.ui.mainScreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.fraisecontrol.R
import com.example.fraisecontrol.ui.mainScreen.CarouselBox
import com.example.fraisecontrol.ui.mainScreen.CarouselBoxItem
import com.example.fraisecontrol.ui.theme.orangeLight

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Carousel(
    modifier: Modifier,
    tempCurrentValue: Int,
    tempTargetValue: Int,
    humCurrentValue: Int,
    humTargetValue: Int,
    lightCurrentValue: Int,
    lightTargetValue: Int,
    onSwipe: (Int) -> Unit,
) {

    val pagerState = rememberPagerState(pageCount = { 3 })
    val listCards = listOf(
        CarouselBoxItem(
            itemName = "Temperature",
            currentValueLabel = "current temp",
            currentValue = "$tempCurrentValue°C",
            targetValueLabel = "target temp",
            targetValue = "$tempTargetValue°C",
            icon = R.drawable.temperature
        ),
        CarouselBoxItem(
            itemName = "Humidity",
            currentValueLabel = "current hum",
            currentValue = "$humCurrentValue%",
            targetValueLabel = "target hum",
            targetValue = "$humTargetValue%",
            icon = R.drawable.humidity
        ),
        CarouselBoxItem(
            itemName = "Lighting",
            currentValueLabel = "current light",
            currentValue = "$lightCurrentValue%",
            targetValueLabel = "target light",
            targetValue = "$lightTargetValue%",
            icon = R.drawable.lighting
        ),

        )
    Box (modifier=modifier){
        Box(modifier = Modifier
            .background(orangeLight)
            .fillMaxWidth()
            .fillMaxHeight(0.3f))
        Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
            HorizontalPager(
                state = pagerState,
                contentPadding = PaddingValues(
                    horizontal = 36.dp
                ),
                pageSpacing = 0.dp,
            ) { page: Int ->
                Column {
                    val item = listCards[page]
                    CarouselBox(
                        itemName = item.itemName,
                        currentValueLabel = item.currentValueLabel,
                        currentValue = item.currentValue,
                        targetValueLabel = item.targetValueLabel,
                        targetValue = item.targetValue,
                        icon = item.icon
                    )

                }
            }
            Row(
                Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(pagerState.pageCount) { iteration ->
                    val color =
                        if (pagerState.currentPage == iteration) orangeLight else Color.DarkGray
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .clip(CircleShape)
                            .background(color)
                            .size(8.dp)
                    )
                }
            }

            LaunchedEffect(key1 = pagerState.currentPage) {
                onSwipe(pagerState.currentPage)
            }

        }
    }
}
