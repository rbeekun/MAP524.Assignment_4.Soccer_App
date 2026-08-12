package com.hello.soccerapp.navigation.appbars

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.hello.soccerapp.navigation.BottomBarNavigationItems

@Composable
fun AppBottomBar(navController: NavController, currentRoute: String?)
{
    NavigationBar {
        // 1. "Club" bottom bar
        NavigationBarItem(
            selected = currentRoute == BottomBarNavigationItems.Team.path,

            onClick = { navController.navigate(BottomBarNavigationItems.Team.path)},

            icon = { Icon(imageVector = BottomBarNavigationItems.Team.icon, contentDescription = BottomBarNavigationItems.Team.title) },

            label = { Text(BottomBarNavigationItems.Team.title) }
        )

        // 2. "Squad" bottom bar
        NavigationBarItem(
            selected = currentRoute == BottomBarNavigationItems.Squad.path,

            onClick = { navController.navigate((BottomBarNavigationItems.Squad.path))},

            icon = { Icon(imageVector = BottomBarNavigationItems.Squad.icon, contentDescription =  BottomBarNavigationItems.Squad.title) },

            label = { Text(BottomBarNavigationItems.Squad.title)}
        )

        // 3. "Favorites" bottom bar
        NavigationBarItem(
            selected = currentRoute == BottomBarNavigationItems.Favorites.path,

            onClick = { navController.navigate(BottomBarNavigationItems.Favorites.path)},

            icon = { Icon(imageVector = BottomBarNavigationItems.Favorites.icon, contentDescription = BottomBarNavigationItems.Favorites.title)},

            label = { Text(BottomBarNavigationItems.Favorites.title)}
        )
    }
}