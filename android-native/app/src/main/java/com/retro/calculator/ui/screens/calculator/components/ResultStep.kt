package com.retro.calculator.ui.screens.calculator.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.retro.calculator.ui.components.RetroButton
import com.retro.calculator.ui.theme.*

@Composable
fun ResultStep(
    result: String,
    quantity: String,
    rate: String,
    unit: String,
    onNewCalculation: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Step indicator
        Text(
            text = "CALCULATION COMPLETE",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.5.sp
            ),
            color = Accent,
            textAlign = TextAlign.Center
        )
        
        // Result display
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(16.dp))
                .background(
                    Brush.linearGradient(
                        colors = listOf(Primary, DarkBlue)
                    )
                )
                .border(
                    width = 3.dp,
                    color = Accent,
                    shape = RoundedCornerShape(16.dp)
                ),
            colors = CardDefaults.cardColors(
                containerColor = Color.Transparent
            )
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "TOTAL AMOUNT",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 12.sp,
                        letterSpacing = 2.sp
                    ),
                    color = Color.White.copy(alpha = 0.8f)
                )
                
                Text(
                    text = result,
                    style = MaterialTheme.typography.displayLarge.copy(
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Black,
                        letterSpacing = 1.sp
                    ),
                    color = Accent,
                    textAlign = TextAlign.Center
                )
                
                Divider(
                    color = Color.White.copy(alpha = 0.3f),
                    thickness = 1.dp
                )
                
                // Calculation breakdown
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        text = "CALCULATION BREAKDOWN",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontSize = 10.sp,
                            letterSpacing = 1.sp
                        ),
                        color = Color.White.copy(alpha = 0.7f)
                    )
                    
                    Text(
                        text = "$quantity $unit × ₹$rate = $result",
                        style = MaterialTheme.typography.bodyLarge.copy(
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium
                        ),
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // New calculation button
        RetroButton(
            onClick = onNewCalculation,
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "NEW CALCULATION",
                style = MaterialTheme.typography.labelLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}