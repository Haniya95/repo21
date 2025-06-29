package com.retro.calculator.ui.screens.calculator.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.retro.calculator.ui.components.RetroButton
import com.retro.calculator.ui.components.RetroOutlinedButton
import com.retro.calculator.ui.theme.*

@Composable
fun RateInputStep(
    rate: String,
    onRateChange: (String) -> Unit,
    onCalculate: () -> Unit,
    onBack: () -> Unit,
    onReset: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Step indicator
        Text(
            text = "STEP 2: ENTER RATE",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.5.sp
            ),
            color = Accent
        )
        
        // Rate input
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Rate per unit (₹):",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Medium
                ),
                color = Color.White
            )
            
            OutlinedTextField(
                value = rate,
                onValueChange = onRateChange,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        Brush.linearGradient(
                            colors = listOf(BgCard, BgDark)
                        )
                    )
                    .border(
                        width = 2.dp,
                        color = Primary,
                        shape = RoundedCornerShape(12.dp)
                    ),
                placeholder = {
                    Text(
                        text = "Enter rate per unit...",
                        color = Color.Gray
                    )
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White,
                    focusedBorderColor = Accent,
                    unfocusedBorderColor = Color.Transparent,
                    cursorColor = Accent
                ),
                singleLine = true
            )
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Action buttons
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            RetroButton(
                onClick = onCalculate,
                enabled = rate.isNotEmpty() && rate.toDoubleOrNull() != null,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.Calculate,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "CALCULATE TOTAL",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )
            }
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                RetroOutlinedButton(
                    onClick = onBack,
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("BACK")
                }
                
                RetroOutlinedButton(
                    onClick = onReset,
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("RESET")
                }
            }
        }
    }
}