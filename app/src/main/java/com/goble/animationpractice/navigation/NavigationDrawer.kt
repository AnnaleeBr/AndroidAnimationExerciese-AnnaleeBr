package com.goble.animationpractice.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Looks3
import androidx.compose.material.icons.filled.Looks4
import androidx.compose.material.icons.filled.Looks5
import androidx.compose.material.icons.filled.Looks6
import androidx.compose.material.icons.filled.LooksOne
import androidx.compose.material.icons.filled.LooksTwo
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.outlined.Looks3
import androidx.compose.material.icons.outlined.Looks4
import androidx.compose.material.icons.outlined.Looks5
import androidx.compose.material.icons.outlined.Looks6
import androidx.compose.material.icons.outlined.LooksOne
import androidx.compose.material.icons.outlined.LooksTwo
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.goble.animationpractice.views.Task1
import com.goble.animationpractice.views.Task2
import com.goble.animationpractice.views.Task3
import com.goble.animationpractice.views.Task4
import com.goble.animationpractice.views.Task5
import com.goble.animationpractice.views.Task6
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawer() {
    val items = listOf(
        NavigationItems(
            title = "Task 1",
            selectedIcon = Icons.Filled.LooksOne,
            unselectedIcon = Icons.Outlined.LooksOne
        ),
        NavigationItems(
            title = "Task 2",
            selectedIcon = Icons.Filled.LooksTwo,
            unselectedIcon = Icons.Outlined.LooksTwo
        ),
        NavigationItems(
            title = "Task 3",
            selectedIcon = Icons.Filled.Looks3,
            unselectedIcon = Icons.Outlined.Looks3
        ),
        NavigationItems(
            title = "Task 4",
            selectedIcon = Icons.Filled.Looks4,
            unselectedIcon = Icons.Outlined.Looks4
        ),
        NavigationItems(
            title = "Task 5",
            selectedIcon = Icons.Filled.Looks5,
            unselectedIcon = Icons.Outlined.Looks5
        ),
        NavigationItems(
            title = "Task 6",
            selectedIcon = Icons.Filled.Looks6,
            unselectedIcon = Icons.Outlined.Looks6
        )
    )

    var selectedItemIndex: Int by rememberSaveable { mutableIntStateOf(0) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Spacer(modifier = Modifier.height(16.dp))

                items.forEachIndexed { index, item ->
                    NavigationDrawerItem(
                        label = {
                            Text(item.title )
                        },
                        selected = index == selectedItemIndex,
                        onClick = {
                            selectedItemIndex = index
                            scope.launch { drawerState.close() }
                        },
                        icon = {
                            Icon(
                                imageVector = if (index == selectedItemIndex) item.selectedIcon else item.unselectedIcon,
                                contentDescription = item.title
                            )
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        },
        gesturesEnabled = true
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text("Animation Exercises")
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    drawerState.apply {
                                        if (isClosed) open() else close()
                                    }
                                }
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menu"
                            )
                        }
                    }
                )
            }
        ) { it ->
            Box(
                modifier = Modifier.padding(it)
            ) {
                when(selectedItemIndex) {
                    0 -> Task1()
                    1 -> Task2()
                    2 -> Task3()
                    3 -> Task4()
                    4 -> Task5()
                    5 -> Task6()
                }
            }
        }
    }
}