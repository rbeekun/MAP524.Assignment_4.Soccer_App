package com.hello.soccerapp.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info

class BottomBarNavigationItems
{
    object Team : navigationItem("main", "Club", Icons.Default.Home)
    object Squad : navigationItem("squad", "Squad", Icons.Default.Face)
    object Favorites : navigationItem("favorites", "Favorites", Icons.Default.Favorite)
}