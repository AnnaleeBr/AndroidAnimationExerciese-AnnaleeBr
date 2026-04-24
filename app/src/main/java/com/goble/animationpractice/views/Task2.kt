package com.goble.animationpractice.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Task2() {
    var isVisible by remember { mutableStateOf(false) }
    Column {
        Button(onClick = {
            isVisible = !isVisible }
        ){
            Text("Toggle Visibility")
        }

        AnimatedVisibility(
            visible = isVisible,
            enter = slideInHorizontally(animationSpec = tween(durationMillis = 500)),
            exit = slideOutVertically(animationSpec = tween(durationMillis = 500))
        ) {
            Box(
                modifier = Modifier.background(color = Color.Red)
            ){
                Text("the box is red")
            }
        }

    }
}



@Preview(showBackground = true)
@Composable
fun PreviewTask2() {
    Task2()
}