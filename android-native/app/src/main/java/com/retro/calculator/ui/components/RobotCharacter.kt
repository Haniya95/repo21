package com.retro.calculator.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.unit.dp
import com.retro.calculator.ui.theme.*

@Composable
fun RobotCharacter(
    modifier: Modifier = Modifier,
    isAnimating: Boolean = false,
    animationType: RobotAnimationType = RobotAnimationType.IDLE
) {
    val infiniteTransition = rememberInfiniteTransition(label = "robot_animations")
    
    // Floating animation
    val floatOffset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = if (isAnimating) -8f else 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        ),
        label = "float_offset"
    )
    
    // Scale animation for excitement
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = when (animationType) {
            RobotAnimationType.EXCITED -> 1.1f
            RobotAnimationType.CONFIRMING -> 1.2f
            else -> 1.02f
        },
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = when (animationType) {
                    RobotAnimationType.EXCITED -> 800
                    RobotAnimationType.CONFIRMING -> 1500
                    else -> 4000
                },
                easing = EaseInOutSine
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale_animation"
    )
    
    // Rotation for confirmation
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = if (animationType == RobotAnimationType.CONFIRMING) 360f else 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = EaseInOutCubic),
            repeatMode = RepeatMode.Restart
        ),
        label = "rotation_animation"
    )
    
    // Eye scanning animation
    val eyeColor by infiniteTransition.animateColor(
        initialValue = Primary,
        targetValue = Success,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        ),
        label = "eye_color"
    )
    
    Box(
        modifier = modifier
            .offset(y = floatOffset.dp)
            .scale(scale)
            .rotate(rotation),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Antenna array
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                repeat(3) { index ->
                    AntennaComponent(
                        height = (32 + index * 8).dp,
                        color = when (index) {
                            0 -> Accent
                            1 -> Success
                            else -> Error
                        }
                    )
                }
            }
            
            // Robot head
            Box(
                modifier = Modifier
                    .size(120.dp, 80.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Primary, BgCard, DarkBlue)
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                // Eyes
                Row(
                    horizontalArrangement = Arrangement.spacedBy(24.dp),
                    modifier = Modifier.offset(y = (-8).dp)
                ) {
                    repeat(2) {
                        Box(
                            modifier = Modifier
                                .size(20.dp)
                                .clip(CircleShape)
                                .background(eyeColor)
                        )
                    }
                }
                
                // Mouth display
                Box(
                    modifier = Modifier
                        .size(32.dp, 8.dp)
                        .offset(y = 16.dp)
                        .clip(RoundedCornerShape(4.dp))
                        .background(Accent)
                )
                
                // Head details
                Box(
                    modifier = Modifier
                        .size(8.dp)
                        .offset(y = (-24).dp)
                        .clip(CircleShape)
                        .background(Accent)
                )
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Robot body
            Box(
                modifier = Modifier
                    .size(140.dp, 120.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(BgCard, Primary, DarkBlue)
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                // Chest panel
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(DarkBlue),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Calculate,
                        contentDescription = null,
                        tint = Accent,
                        modifier = Modifier.size(32.dp)
                    )
                }
                
                // Control panels
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomCenter)
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    ControlPanel(color = Accent)
                    ControlPanel(color = Success)
                }
                
                // Arms
                Box(
                    modifier = Modifier
                        .size(16.dp, 60.dp)
                        .offset(x = (-70).dp, y = (-10).dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Primary, DarkBlue)
                            )
                        )
                )
                
                Box(
                    modifier = Modifier
                        .size(16.dp, 60.dp)
                        .offset(x = 70.dp, y = (-10).dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(Primary, DarkBlue)
                            )
                        )
                )
            }
            
            Spacer(modifier = Modifier.height(8.dp))
            
            // Robot base
            Box(
                modifier = Modifier
                    .size(100.dp, 24.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(Primary, DarkBlue)
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    repeat(3) { index ->
                        Box(
                            modifier = Modifier
                                .size(6.dp)
                                .clip(CircleShape)
                                .background(
                                    when (index) {
                                        0 -> Accent
                                        1 -> Success
                                        else -> Error
                                    }
                                )
                        )
                    }
                }
            }
            
            Spacer(modifier = Modifier.height(12.dp))
            
            // Robot legs
            Row(
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                repeat(2) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Box(
                            modifier = Modifier
                                .size(16.dp, 40.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(Primary, DarkBlue)
                                    )
                                )
                        )
                        
                        Box(
                            modifier = Modifier
                                .size(24.dp, 12.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .background(DarkBlue)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun AntennaComponent(
    height: Dp,
    color: Color
) {
    val infiniteTransition = rememberInfiniteTransition(label = "antenna")
    val pulse by infiniteTransition.animateFloat(
        initialValue = 0.8f,
        targetValue = 1.3f,
        animationSpec = infiniteRepeatable(
            animation = tween(1500, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        ),
        label = "antenna_pulse"
    )
    
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(6.dp, height)
                .clip(RoundedCornerShape(3.dp))
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Primary, Accent)
                    )
                )
        )
        
        Box(
            modifier = Modifier
                .size(12.dp)
                .scale(pulse)
                .clip(CircleShape)
                .background(color)
        )
    }
}

@Composable
private fun ControlPanel(color: Color) {
    val infiniteTransition = rememberInfiniteTransition(label = "control_panel")
    val glow by infiniteTransition.animateFloat(
        initialValue = 0.6f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = EaseInOutSine),
            repeatMode = RepeatMode.Reverse
        ),
        label = "control_glow"
    )
    
    Box(
        modifier = Modifier
            .size(16.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(color.copy(alpha = glow))
    )
}

enum class RobotAnimationType {
    IDLE,
    EXCITED,
    CONFIRMING,
    WAVING
}