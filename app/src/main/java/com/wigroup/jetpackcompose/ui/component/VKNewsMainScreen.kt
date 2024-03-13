package com.wigroup.jetpackcompose.ui.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.wigroup.jetpackcompose.MainViewModel

@Composable
fun MainScreen(
    viewModel: MainViewModel
) {
    Scaffold(
        bottomBar = {
            NavigationBar {
                val selectedItemPosition = remember {
                    mutableIntStateOf(0)
                }
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
        InstagramProfileCard(viewModel = viewModel, modifier = Modifier.padding(it).padding(8.dp))
    }
}