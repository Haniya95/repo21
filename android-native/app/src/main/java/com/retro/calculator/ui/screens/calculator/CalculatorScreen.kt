package com.retro.calculator.ui.screens.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.retro.calculator.ui.components.RobotCharacter
import com.retro.calculator.ui.components.RobotAnimationType
import com.retro.calculator.ui.screens.calculator.components.*
import com.retro.calculator.ui.theme.*
import com.retro.calculator.utils.HapticFeedback

@Composable
fun CalculatorScreen(
    viewModel: CalculatorViewModel = viewModel()
) {
    val context = LocalContext.current
    val haptic = remember { HapticFeedback(context) }
    val uiState by viewModel.uiState.collectAsState()
    
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.radialGradient(
                    colors = listOf(BgCard, BgDark, BgDark),
                    radius = 1000f
                )
            )
    ) {
        // Left Panel - Robot and Chat
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Header
            CalculatorHeader()
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Robot Character
            RobotCharacter(
                modifier = Modifier.size(160.dp),
                isAnimating = true,
                animationType = when {
                    uiState.isCalculating -> RobotAnimationType.EXCITED
                    uiState.showResult -> RobotAnimationType.CONFIRMING
                    else -> RobotAnimationType.IDLE
                }
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            // Chat Bubble
            if (uiState.robotMessage.isNotEmpty()) {
                RobotChatBubble(
                    message = uiState.robotMessage,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
        
        // Right Panel - Calculator Interface
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(BgCard, BgDark)
                    )
                )
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            when (uiState.currentStep) {
                CalculatorStep.QUANTITY_INPUT -> {
                    QuantityInputStep(
                        quantity = uiState.quantity,
                        selectedUnit = uiState.selectedUnit,
                        onQuantityChange = { 
                            haptic.performHapticFeedback()
                            viewModel.updateQuantity(it) 
                        },
                        onUnitChange = { 
                            haptic.performHapticFeedback()
                            viewModel.updateUnit(it) 
                        },
                        onNext = { 
                            haptic.performHapticFeedback()
                            viewModel.proceedToRateInput() 
                        },
                        onReset = { 
                            haptic.performHapticFeedback()
                            viewModel.reset() 
                        }
                    )
                }
                
                CalculatorStep.RATE_INPUT -> {
                    RateInputStep(
                        rate = uiState.rate,
                        onRateChange = { 
                            haptic.performHapticFeedback()
                            viewModel.updateRate(it) 
                        },
                        onCalculate = { 
                            haptic.performHapticFeedback()
                            viewModel.calculate() 
                        },
                        onBack = { 
                            haptic.performHapticFeedback()
                            viewModel.goBack() 
                        },
                        onReset = { 
                            haptic.performHapticFeedback()
                            viewModel.reset() 
                        }
                    )
                }
                
                CalculatorStep.RESULT -> {
                    ResultStep(
                        result = uiState.result,
                        quantity = uiState.quantity,
                        rate = uiState.rate,
                        unit = uiState.selectedUnit,
                        onNewCalculation = { 
                            haptic.performHapticFeedback()
                            viewModel.reset() 
                        }
                    )
                }
            }
        }
    }
}