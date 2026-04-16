package com.goble.animationpractice.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Task6() {
    val items = remember { (1..5).map { "Item $it" } }

    LazyColumn(
        modifier = Modifier.fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(items) { index, item ->
            // TODO: Create animated list item here
            // remember to user index for staggered animation
        }
    }
}

@Composable
fun AnimatedListItem(
    title: String,
    index: Int
) {
    // TODO Implement the list item with animations
    // use remember to track whether item should be visible
    // calculate delay based on index
}