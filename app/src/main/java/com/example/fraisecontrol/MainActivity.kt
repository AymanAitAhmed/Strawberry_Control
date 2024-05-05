package com.example.fraisecontrol

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.fraisecontrol.ui.mainScreen.MainScreen
import com.example.fraisecontrol.ui.mainScreen.MainScreenViewModel
import com.example.fraisecontrol.ui.theme.FraiseControlTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FraiseControlTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel : MainScreenViewModel= viewModel()

                    val tempCurrentValue = viewModel.tempCurrentValue.collectAsStateWithLifecycle()
                    val tempTargetValue = viewModel.tempTargetValue.collectAsStateWithLifecycle()
                    val humCurrentValue = viewModel.humCurrentValue.collectAsStateWithLifecycle()
                    val humTargetValue = viewModel.humTargetValue.collectAsStateWithLifecycle()
                    val lightCurrentValue = viewModel.lightCurrentValue.collectAsStateWithLifecycle()
                    val lightTargetValue = viewModel.lightTargetValue.collectAsStateWithLifecycle()
                    val valueSymbol = viewModel.valueSymbol.collectAsStateWithLifecycle()
                    val indicatorValue = viewModel.indicatorValue.collectAsStateWithLifecycle()
                    val maxValue = viewModel.maxValue.collectAsStateWithLifecycle()
                    val minValue = viewModel.minValue.collectAsStateWithLifecycle()

                    MainScreen(
                        tempCurrentValue = tempCurrentValue.value,
                        tempTargetValue = tempTargetValue.value,
                        humCurrentValue = humCurrentValue.value,
                        humTargetValue = humTargetValue.value,
                        lightCurrentValue = lightCurrentValue.value,
                        lightTargetValue = lightTargetValue.value,
                        valueSymbol = valueSymbol.value,
                        indicatorValue = indicatorValue.value,
                        maxValue = maxValue.value,
                        minValue = minValue.value,
                        onSwipe = viewModel::onSwipe,
                        onTargetValueIncrease = viewModel::onTargetValueIncrease,
                        onTargetValueDecrease = viewModel::onTargetValueDecrease,
                    )
                }
            }
        }
    }
}

