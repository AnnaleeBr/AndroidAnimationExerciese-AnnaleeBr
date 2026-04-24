package com.goble.animationpractice.views

import androidx.compose.animation.animateColor
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Task5() {
    val infiniteTransition = rememberInfiniteTransition()

    val color by
    infiniteTransition.animateColor(
        initialValue = Color(0xff0E402D),
        targetValue = Color(0xffD6FFB7),
        animationSpec =
            infiniteRepeatable(
                 animation = tween(3000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse,
            ),
        label="color"
    )
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier.size(80.dp)
                    .clip(CircleShape)
                    .background(color)
            )

            Spacer(modifier = Modifier.height(16.dp))

            val alpha by infiniteTransition.animateColor(
                initialValue=Color(0x000f0f0f),
                targetValue = Color(0xff0f0f0f),
                animationSpec =
                    infiniteRepeatable(
                        animation = tween(1000, easing = LinearEasing),
                        repeatMode = RepeatMode.Reverse,
                    ),
                label="alpha"
            )
            Text("Loading...", color=alpha)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTask5() {
    Task5()
}