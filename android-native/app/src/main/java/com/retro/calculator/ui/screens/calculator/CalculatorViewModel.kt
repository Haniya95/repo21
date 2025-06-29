package com.retro.calculator.ui.screens.calculator

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.DecimalFormat

class CalculatorViewModel : ViewModel() {
    
    private val _uiState = MutableStateFlow(CalculatorUiState())
    val uiState: StateFlow<CalculatorUiState> = _uiState.asStateFlow()
    
    private val decimalFormat = DecimalFormat("#,##0.000")
    private val currencyFormat = DecimalFormat("#,##0.00")
    
    init {
        updateRobotMessage("Welcome! Let's calculate some unit prices together. Start by entering a quantity.")
    }
    
    fun updateQuantity(quantity: String) {
        _uiState.value = _uiState.value.copy(quantity = quantity)
        
        if (quantity.isNotEmpty()) {
            updateRobotMessage("Great! You entered $quantity ${_uiState.value.selectedUnit}. Now let's set the rate per unit.")
        }
    }
    
    fun updateUnit(unit: String) {
        _uiState.value = _uiState.value.copy(selectedUnit = unit)
        updateRobotMessage("Perfect! We're calculating for $unit. Enter your quantity and we'll proceed.")
    }
    
    fun updateRate(rate: String) {
        _uiState.value = _uiState.value.copy(rate = rate)
        
        if (rate.isNotEmpty()) {
            updateRobotMessage("Excellent! Rate set to ₹$rate per ${_uiState.value.selectedUnit}. Ready to calculate!")
        }
    }
    
    fun proceedToRateInput() {
        val quantity = _uiState.value.quantity.toDoubleOrNull()
        if (quantity != null && quantity > 0) {
            _uiState.value = _uiState.value.copy(currentStep = CalculatorStep.RATE_INPUT)
            updateRobotMessage("Now enter the rate per ${_uiState.value.selectedUnit} in rupees.")
        } else {
            updateRobotMessage("Please enter a valid quantity greater than 0.")
        }
    }
    
    fun calculate() {
        val quantity = _uiState.value.quantity.toDoubleOrNull()
        val rate = _uiState.value.rate.toDoubleOrNull()
        
        if (quantity != null && rate != null && quantity > 0 && rate > 0) {
            _uiState.value = _uiState.value.copy(isCalculating = true)
            updateRobotMessage("Calculating... Processing your request!")
            
            viewModelScope.launch {
                delay(1500) // Simulate calculation time
                
                val result = quantity * rate
                val formattedResult = if (_uiState.value.selectedUnit.contains("kg") || 
                                        _uiState.value.selectedUnit.contains("gram")) {
                    decimalFormat.format(result)
                } else {
                    currencyFormat.format(result)
                }
                
                _uiState.value = _uiState.value.copy(
                    result = "₹$formattedResult",
                    currentStep = CalculatorStep.RESULT,
                    isCalculating = false,
                    showResult = true
                )
                
                updateRobotMessage("Calculation complete! ${_uiState.value.quantity} ${_uiState.value.selectedUnit} at ₹${_uiState.value.rate} per unit = ₹$formattedResult total.")
            }
        } else {
            updateRobotMessage("Please enter valid numbers for both quantity and rate.")
        }
    }
    
    fun goBack() {
        _uiState.value = _uiState.value.copy(currentStep = CalculatorStep.QUANTITY_INPUT)
        updateRobotMessage("Back to quantity input. Modify your values as needed.")
    }
    
    fun reset() {
        _uiState.value = CalculatorUiState()
        updateRobotMessage("Reset complete! Let's start fresh with a new calculation.")
    }
    
    private fun updateRobotMessage(message: String) {
        _uiState.value = _uiState.value.copy(robotMessage = message)
    }
}

data class CalculatorUiState(
    val currentStep: CalculatorStep = CalculatorStep.QUANTITY_INPUT,
    val quantity: String = "",
    val selectedUnit: String = "kg",
    val rate: String = "",
    val result: String = "",
    val robotMessage: String = "",
    val isCalculating: Boolean = false,
    val showResult: Boolean = false
)

enum class CalculatorStep {
    QUANTITY_INPUT,
    RATE_INPUT,
    RESULT
}