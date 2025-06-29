package com.retro.calculator.ui.screens.calculator.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.retro.calculator.ui.theme.*

@Composable
fun CalculatorHeader() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(vertical = 16.dp)
    ) {
        Text(
            text = "RETRO CALCULATOR",
            style = MaterialTheme.typography.displayMedium.copy(
                fontSize = 20.sp,
                fontWeight = FontWeight.Black,
                letterSpacing = 2.sp
            ),
            color = Accent,
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(4.dp))
        
        Text(
            text = "QUANTITY × RATE = TOTAL",
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 12.sp,
                letterSpacing = 1.sp
            ),
            color = Primary,
            textAlign = TextAlign.Center
        )
    }
}