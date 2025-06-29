package com.retro.calculator.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.retro.calculator.ui.theme.*

@Composable
fun RetroButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    content: @Composable RowScope.() -> Unit
) {
    Button(
        onClick = onClick,
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(
                Brush.linearGradient(
                    colors = if (enabled) {
                        listOf(Primary, DarkBlue)
                    } else {
                        listOf(Color.Gray, Color.DarkGray)
                    }
                )
            )
            .border(
                width = 2.dp,
                color = if (enabled) Primary else Color.Gray,
                shape = RoundedCornerShape(12.dp)
            ),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Transparent,
            contentColor = Color.White,
            disabledContainerColor = Color.Transparent,
            disabledContentColor = Color.Gray
        ),
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp),
        content = content
    )
}

@Composable
fun RetroOutlinedButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .border(
                width = 2.dp,
                color = if (enabled) Primary else Color.Gray,
                shape = RoundedCornerShape(12.dp)
            ),
        enabled = enabled,
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = if (enabled) Primary else Color.Gray,
            disabledContentColor = Color.Gray
        ),
        border = null,
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp),
        content = content
    )
}