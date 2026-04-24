package com.goble.animationpractice.views

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.unit.dp

@Composable
fun Task6() {
    val items = remember { (1..8).map { "Item $it" } }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(items) { index, item ->
            AnimatedListItem(item, index)
        }
    }
}

@Composable
fun AnimatedListItem(
    title: String,
    index: Int
) {
    var isVisible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit){
        isVisible=true
    }

    val alpha by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = tween(durationMillis=800*index),
        label = "fade"
    )

    //idk if this is far down enough but i think it looks cute so
    val offset by animateDpAsState(
        targetValue = if (isVisible) 0.dp else (300).dp,
        animationSpec = tween(durationMillis=800*index),
        label = "slide"
    )

    //the card is ugly but alas
    Card(
        modifier=Modifier.offset(x=0.dp, y=offset)
    ){
        Text(title, modifier=Modifier.alpha(alpha))
    }
}