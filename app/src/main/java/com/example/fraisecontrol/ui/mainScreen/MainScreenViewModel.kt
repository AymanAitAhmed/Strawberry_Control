package com.example.fraisecontrol.ui.mainScreen

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.fraisecontrol.network.MqttRepository
import com.example.fraisecontrol.utils.HUMMAX
import com.example.fraisecontrol.utils.HUMMIN
import com.example.fraisecontrol.utils.HUMSYMBOL
import com.example.fraisecontrol.utils.LIGHTMAX
import com.example.fraisecontrol.utils.LIGHTMIN
import com.example.fraisecontrol.utils.LIGHTSYMBOL
import com.example.fraisecontrol.utils.TEMPMAX
import com.example.fraisecontrol.utils.TEMPMIN
import com.example.fraisecontrol.utils.TEMPSYMBOL
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    val mqttRepo: MqttRepository
) : ViewModel() {

    private val _tempCurrentValue = MutableStateFlow(21)
    val tempCurrentValue = _tempCurrentValue.asStateFlow()

    private val _tempTargetValue = MutableStateFlow(25)
    val tempTargetValue = _tempTargetValue.asStateFlow()

    private val _humCurrentValue = MutableStateFlow(68)
    val humCurrentValue = _humCurrentValue.asStateFlow()

    private val _humTargetValue = MutableStateFlow(78)
    val humTargetValue = _humTargetValue.asStateFlow()

    private val _lightCurrentValue = MutableStateFlow(58)
    val lightCurrentValue = _lightCurrentValue.asStateFlow()

    private val _lightTargetValue = MutableStateFlow(80)
    val lightTargetValue = _lightTargetValue.asStateFlow()

    private val _valueSymbol = MutableStateFlow(TEMPSYMBOL)
    val valueSymbol = _valueSymbol.asStateFlow()

    private val _indicatorValue = MutableStateFlow(_tempTargetValue.value)
    val indicatorValue = _indicatorValue.asStateFlow()

    private val _maxValue = MutableStateFlow(TEMPMAX)
    val maxValue = _maxValue.asStateFlow()

    private val _minValue = MutableStateFlow(TEMPMIN)
    val minValue = _minValue.asStateFlow()

    private val _page = MutableStateFlow(0)
    val page = _page.asStateFlow()

    fun onSwipe(newPage: Int) {
        when (newPage) {
            0 -> {
                _page.value = newPage
                _valueSymbol.value = TEMPSYMBOL
                _indicatorValue.value = _tempTargetValue.value
                _maxValue.value = TEMPMAX
                _minValue.value = TEMPMIN
            }

            1 -> {
                _page.value = newPage
                _valueSymbol.value = HUMSYMBOL
                _indicatorValue.value = humTargetValue.value
                _maxValue.value = HUMMAX
                _minValue.value = HUMMIN
            }

            2 -> {
                _page.value = newPage
                _valueSymbol.value = LIGHTSYMBOL
                _indicatorValue.value = lightTargetValue.value
                _maxValue.value = LIGHTMAX
                _minValue.value = LIGHTMIN
            }
        }
    }

    fun onTargetValueIncrease() {
        mqttRepo.connect()
        when (_page.value) {
            0 -> {
                if (_tempTargetValue.value < TEMPMAX) {
                    _tempTargetValue.value += 1
                    _indicatorValue.value = _tempTargetValue.value
                }
            }

            1 -> {
                if (_humTargetValue.value < HUMMAX) {
                    _humTargetValue.value += 1
                    _indicatorValue.value = humTargetValue.value
                }
            }

            2 -> {
                if (_lightTargetValue.value < LIGHTMAX) {
                    _lightTargetValue.value += 1
                    _indicatorValue.value = lightTargetValue.value
                }
            }
        }
    }

    fun onTargetValueDecrease() {
        mqttRepo.subscribe()
        when (_page.value) {
            0 -> {
                if (_tempTargetValue.value > TEMPMIN) {
                    _tempTargetValue.value -= 1
                    _indicatorValue.value = _tempTargetValue.value
                }
            }

            1 -> {
                if (_humTargetValue.value > HUMMIN) {
                    _humTargetValue.value -= 1
                    _indicatorValue.value = humTargetValue.value
                }
            }

            2 -> {
                if (_lightTargetValue.value > LIGHTMIN) {
                    _lightTargetValue.value -= 1
                    _indicatorValue.value = lightTargetValue.value
                }
            }
        }
    }

    fun initMqtt(){
        mqttRepo.connect()
    }
}