package com.goble.animationpractice.views

import androidx.compose.animation.core.EaseInBounce
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun Task3() {
    var isAnimating by remember { mutableStateOf(true) }
    var ballCount by remember { mutableIntStateOf(1) }
    var useLinearEasing by remember { mutableStateOf(false) }
    val color1 = Color(0xFF3e196e)
    val color2 = Color(0xFFd46c76)
    val color3 = Color(0xFFffc07c)
    val brush = Brush.verticalGradient(listOf(color1, color2, color3))

    Box(
        modifier = Modifier.fillMaxSize()
            .background(brush)
    ) {
        Text(
            text = "Bounding Ball Animation",
            modifier = Modifier
                .padding(top = 32.dp)
                .align(Alignment.TopCenter),
            fontSize = MaterialTheme.typography.headlineSmall.fontSize,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        repeat(ballCount) { index ->
            BouncingBall(
                isAnimating = isAnimating,
                delayMillis = index * 200,
                hueOffset = (index * 60f) % 360f,
                useLinearEasing = useLinearEasing,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 120.dp + (index * 120).dp)
            )
        }

        ControlPanel(
            isAnimating = isAnimating,
            onToggleAnimation = { isAnimating = !isAnimating },
            onToggleEasing = { useLinearEasing = !useLinearEasing },
            useLinearEasing = useLinearEasing,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(24.dp)
        )
    }
}

@Composable
fun BouncingBall(
    isAnimating: Boolean,
    delayMillis: Int,
    hueOffset: Float,
    useLinearEasing: Boolean,
    modifier: Modifier = Modifier
) {
    val screenHeight = LocalConfiguration.current.screenHeightDp.dp
    val ballSize = 60.dp
    val maxBounceDistance = screenHeight - ballSize - 150.dp

    val verticalProgress by animateFloatAsState(
        targetValue = if (isAnimating) 1f else 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                delayMillis = delayMillis,
                easing = if (useLinearEasing) LinearEasing else EaseInBounce
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "ball vertical bounce"
    )

    val hueProgress by animateFloatAsState(
        targetValue = if (isAnimating) 360f else 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 3000,
                delayMillis = delayMillis
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "ballColorHue"
    )

    // Shadow opacity based on height (more opaque when near ground)
    val shadowOpacity = 1f - verticalProgress

    // Calculate scale based on bounce (compress slightly at impact)
    var scaleY = 0.95f + (verticalProgress * 0.05f)

    Box(modifier = modifier) {
        // Shadow effect
        Box(
            modifier = Modifier
                .width(ballSize)
                .height(8.dp)
                .offset(y = maxBounceDistance + ballSize + 8.dp)
                .background(
                    color = Color.Black.copy(alpha = shadowOpacity * 0.3f),
                    shape = RoundedCornerShape(50)
                )
                .graphicsLayer {
                    scaleX = 1f - (verticalProgress * 0.3f)
                    alpha = shadowOpacity
                }
        )

        // Main ball
        Box(
            modifier = Modifier
                .size(ballSize)
                .offset(y = maxBounceDistance * verticalProgress)
                .background(
                    color = Color.hsv(
                        hue = (hueProgress + hueOffset) % 360f,
                        saturation = 0.85f,
                        value = 0.95f
                    ),
                    shape = CircleShape
                )
                .graphicsLayer {
                    scaleY = scaleY
                    shadowElevation = (1f - verticalProgress) * 12f
                }
        )
    }
}

@Composable
fun ControlPanel(
    isAnimating: Boolean,
    onToggleAnimation: () -> Unit,
    onToggleEasing: () -> Unit,
    useLinearEasing: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.background(
            color = Color(0xFF1a1a2e),
            shape = RoundedCornerShape(16.dp)
        )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Button(
            onClick = onToggleAnimation,
            modifier = Modifier.fillMaxWidth()
                .height(48.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isAnimating) Color(0xFF2ecc71) else Color(0xFF3498db)
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            Icon(
                imageVector = if (isAnimating) Icons.Filled.Pause else Icons.Filled.PlayArrow,
                contentDescription = if (isAnimating) "Pause" else "Play",
                modifier = Modifier.padding(end = 8.dp)
            )

            Text(if (isAnimating) "Pause Animation" else "Resume Animation")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = onToggleEasing,
            modifier = Modifier.fillMaxWidth()
                .height(40.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF9B59B6)
            ),
            shape = RoundedCornerShape(6.dp)
        ){
            Text(
                "Easing ${if (useLinearEasing) "Linear" else "EaseInBounce" }",
                fontSize = MaterialTheme.typography.labelSmall.fontSize
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewTask3() {
    Task3()
}