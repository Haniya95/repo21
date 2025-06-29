package com.retro.calculator.ui.screens.calculator.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
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
fun QuantityInputStep(
    quantity: String,
    selectedUnit: String,
    onQuantityChange: (String) -> Unit,
    onUnitChange: (String) -> Unit,
    onNext: () -> Unit,
    onReset: () -> Unit
) {
    val units = listOf(
        "kg", "gram", "quintal", "ton",
        "liter", "ml", "gallon",
        "piece", "dozen", "box"
    )
    
    var expanded by remember { mutableStateOf(false) }
    
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        // Step indicator
        Text(
            text = "STEP 1: ENTER QUANTITY",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.5.sp
            ),
            color = Accent
        )
        
        // Quantity input
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Quantity:",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Medium
                ),
                color = Color.White
            )
            
            OutlinedTextField(
                value = quantity,
                onValueChange = onQuantityChange,
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
                        text = "Enter quantity...",
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
        
        // Unit selection
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Unit:",
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.Medium
                ),
                color = Color.White
            )
            
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedTextField(
                    value = selectedUnit,
                    onValueChange = {},
                    readOnly = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor()
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
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedBorderColor = Color.Transparent,
                        unfocusedBorderColor = Color.Transparent
                    )
                )
                
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.background(BgCard)
                ) {
                    units.forEach { unit ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = unit,
                                    color = Color.White
                                )
                            },
                            onClick = {
                                onUnitChange(unit)
                                expanded = false
                            },
                            colors = MenuDefaults.itemColors(
                                textColor = Color.White
                            )
                        )
                    }
                }
            }
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Action buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
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
            
            RetroButton(
                onClick = onNext,
                enabled = quantity.isNotEmpty() && quantity.toDoubleOrNull() != null,
                modifier = Modifier.weight(1f)
            ) {
                Text("NEXT")
                Spacer(modifier = Modifier.width(4.dp))
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
            }
        }
    }
}