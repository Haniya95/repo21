package com.retro.calculator.ui.screens.calculator.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.retro.calculator.ui.theme.*

@Composable
fun RobotChatBubble(
    message: String,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(90.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(Primary, DarkBlue)
                )
            )
            .border(
                width = 2.dp,
                color = Accent,
                shape = RoundedCornerShape(15.dp)
            )
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 11.sp,
                lineHeight = 14.sp
            ),
            color = androidx.compose.ui.graphics.Color.White,
            textAlign = TextAlign.Center,
            maxLines = 4
        )
    }
}