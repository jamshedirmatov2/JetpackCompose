package com.wigroup.jetpackcompose.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.wigroup.jetpackcompose.R

sealed class NavigationItem(
    val titleResId: Int,
    val icon: ImageVector,
) {

    data object Home : NavigationItem(
        titleResId = R.string.navigation_item0,
        icon = Icons.Outlined.Home,
    )

    data object Favourite : NavigationItem(
        titleResId = R.string.navigation_item1,
        icon = Icons.Outlined.Favorite,
    )

    data object Profile : NavigationItem(
        titleResId = R.string.navigation_item2,
        icon = Icons.Outlined.Person,
    )
}