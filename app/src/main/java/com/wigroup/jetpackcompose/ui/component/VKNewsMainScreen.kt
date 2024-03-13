package com.wigroup.jetpackcompose.ui.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch

@Preview
@Composable
fun MainScreen() {
    val snackBarHostState = remember {
        SnackbarHostState()
    }
    val scope = rememberCoroutineScope()
    val fabVisibility = remember {
        mutableStateOf(true)
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
        floatingActionButton = {
            if (fabVisibility.value) {
                FloatingActionButton(onClick = {
                    scope.launch {
                        val action = snackBarHostState.showSnackbar(
                            message = "This is worked",
                            actionLabel = "Done",
                            duration = SnackbarDuration.Long,
                        )
                        if (action == SnackbarResult.ActionPerformed) {
                            fabVisibility.value = false
                        }
                    }
                }) {
                    Icon(imageVector = Icons.Filled.Favorite, contentDescription = null)
                }
            }
        },
        bottomBar = {
            NavigationBar {
                println("NavigationBar")
                val selectedItemPosition = remember {
                    println("remember")
                    mutableIntStateOf(0)
                }
                println(selectedItemPosition.intValue)
                val items = listOf(
                    NavigationItem.Home,
                    NavigationItem.Favourite,
                    NavigationItem.Profile,
                )

                items.forEachIndexed { index, it ->
                    NavigationBarItem(
                        selected = selectedItemPosition.intValue == index,
                        onClick = { selectedItemPosition.intValue = index },
                        icon = { Icon(it.icon, contentDescription = null) },
                        label = { Text(text = stringResource(id = it.titleResId)) },
                    )
                }
            }
        }
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            color = MaterialTheme.colorScheme.background,
        ) {}
    }
}