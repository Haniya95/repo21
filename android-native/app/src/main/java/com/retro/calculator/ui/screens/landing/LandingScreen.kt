package com.retro.calculator.ui.screens.landing

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.RocketLaunch
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.retro.calculator.ui.components.RetroButton
import com.retro.calculator.ui.components.RobotCharacter
import com.retro.calculator.ui.theme.*
import com.retro.calculator.utils.HapticFeedback

@Composable
fun LandingScreen(
    onEnterCalculator: () -> Unit
) {
    val context = LocalContext.current
    val haptic = remember { HapticFeedback(context) }
    
    // Animation states
    val infiniteTransition = rememberInfiniteTransition(label = "landing_animations")
    
    val gridRotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(20000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "grid_rotation"
    )
    
    val titleGlow by infiniteTransition.animateFloat(
        initialValue = 0.3f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        ),
        label = "title_glow"
    )
    
    val buttonPulse by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        ),
        label = "button_pulse"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.radialGradient(
                    colors = listOf(BgCard, BgDark, BgDark),
                    radius = 800f
                )
            )
    ) {
        // Animated background grid
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            drawAnimatedGrid(gridRotation)
        }
        
        // Floating particles
        repeat(6) { index ->
            FloatingParticle(
                index = index,
                modifier = Modifier.fillMaxSize()
            )
        }
        
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Robot Character
            RobotCharacter(
                modifier = Modifier.size(200.dp),
                isAnimating = true
            )
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // Main Title with glow effect
            Text(
                text = "QUANTITY PRICE",
                style = MaterialTheme.typography.displayLarge.copy(
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = 3.sp
                ),
                color = Accent.copy(alpha = titleGlow),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            
            Text(
                text = "CALCULATOR",
                style = MaterialTheme.typography.displayMedium.copy(
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 4.sp
                ),
                color = Primary.copy(alpha = titleGlow),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            // Subtitle
            Text(
                text = "Calculate precise unit prices and quantities with our advanced retro calculator assistant",
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 12.sp,
                    letterSpacing = 1.sp
                ),
                color = Color.White.copy(alpha = 0.9f),
                textAlign = TextAlign.Center,
                lineHeight = 18.sp,
                modifier = Modifier.padding(horizontal = 32.dp)
            )
            
            Spacer(modifier = Modifier.height(40.dp))
            
            // Enter button with pulse animation
            RetroButton(
                onClick = {
                    haptic.performHapticFeedback()
                    onEnterCalculator()
                },
                modifier = Modifier
                    .scale(buttonPulse)
                    .padding(horizontal = 32.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Primary,
                    contentColor = Color.White
                )
            ) {
                Icon(
                    imageVector = Icons.Default.RocketLaunch,
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "ENTER CALCULATOR",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 2.sp
                    )
                )
            }
            
            Spacer(modifier = Modifier.height(32.dp))
            
            // Version info
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "RETRO-BOT CALCULATOR v2.0",
                    style = MaterialTheme.typography.labelLarge.copy(
                        fontSize = 10.sp,
                        letterSpacing = 1.5.sp
                    ),
                    color = Primary.copy(alpha = 0.8f)
                )
                
                Spacer(modifier = Modifier.height(8.dp))
                
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    StatusIndicator(color = Success)
                    StatusIndicator(color = Accent)
                    StatusIndicator(color = Error)
                }
            }
        }
    }
}

@Composable
private fun StatusIndicator(color: Color) {
    val infiniteTransition = rememberInfiniteTransition(label = "status_indicator")
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.4f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        ),
        label = "status_alpha"
    )
    
    Box(
        modifier = Modifier
            .size(6.dp)
            .clip(CircleShape)
            .background(color.copy(alpha = alpha))
    )
}

@Composable
private fun FloatingParticle(
    index: Int,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "particle_$index")
    
    val offsetY by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 50f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 3000 + (index * 500),
                easing = EaseInOutSine
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "particle_offset_y"
    )
    
    val alpha by infiniteTransition.animateFloat(
        initialValue = 0.2f,
        targetValue = 0.6f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 2000 + (index * 300),
                easing = EaseInOutSine
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "particle_alpha"
    )
    
    val positions = listOf(
        Offset(0.1f, 0.2f),
        Offset(0.9f, 0.15f),
        Offset(0.15f, 0.8f),
        Offset(0.85f, 0.75f),
        Offset(0.3f, 0.1f),
        Offset(0.7f, 0.9f)
    )
    
    val colors = listOf(Accent, Primary, Success, Error, Color.Cyan, Color.Magenta)
    
    Canvas(modifier = modifier) {
        val position = positions[index % positions.size]
        val color = colors[index % colors.size]
        
        drawCircle(
            color = color.copy(alpha = alpha),
            radius = 3.dp.toPx(),
            center = Offset(
                x = size.width * position.x,
                y = size.height * position.y + offsetY
            )
        )
    }
}

private fun DrawScope.drawAnimatedGrid(rotation: Float) {
    val gridSize = 40.dp.toPx()
    val strokeWidth = 1.dp.toPx()
    
    rotate(rotation, center) {
        // Vertical lines
        var x = 0f
        while (x <= size.width + gridSize) {
            drawLine(
                color = Primary.copy(alpha = 0.1f),
                start = Offset(x, 0f),
                end = Offset(x, size.height),
                strokeWidth = strokeWidth
            )
            x += gridSize
        }
        
        // Horizontal lines
        var y = 0f
        while (y <= size.height + gridSize) {
            drawLine(
                color = Primary.copy(alpha = 0.1f),
                start = Offset(0f, y),
                end = Offset(size.width, y),
                strokeWidth = strokeWidth
            )
            y += gridSize
        }
    }
}