package com.retro.calculator.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.retro.calculator.ui.screens.landing.LandingScreen
import com.retro.calculator.ui.screens.calculator.CalculatorScreen

@Composable
fun RetroCalculatorApp() {
    val navController = rememberNavController()
    
    NavHost(
        navController = navController,
        startDestination = "landing",
        modifier = Modifier.fillMaxSize()
    ) {
        composable("landing") {
            LandingScreen(
                onEnterCalculator = {
                    navController.navigate("calculator") {
                        popUpTo("landing") { inclusive = true }
                    }
                }
            )
        }
        
        composable("calculator") {
            CalculatorScreen()
        }
    }
}