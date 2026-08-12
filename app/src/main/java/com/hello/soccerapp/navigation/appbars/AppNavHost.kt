package com.hello.soccerapp.navigation.appbars

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hello.soccerapp.UILayer.UILayer.FavoriteUILayer.FavoritesScreen
import com.hello.soccerapp.UILayer.UILayer.FavoriteUILayer.FavoritesViewModel
import com.hello.soccerapp.UILayer.UILayer.PlayerUILayer.PlayerScreen
import com.hello.soccerapp.UILayer.UILayer.SearchUILayer.SearchScreen
import com.hello.soccerapp.UILayer.UILayer.SquadUILayer.SquadScreen
import com.hello.soccerapp.UILayer.UILayer.TeamUILayer.TeamScreen
import com.hello.soccerapp.UILayer.UILayer.TeamUILayer.TeamViewModel
import com.hello.soccerapp.navigation.BottomBarNavigationItems

@Composable
fun AppNavHost(navController: NavHostController, teamViewModel: TeamViewModel ,modifier: Modifier = Modifier,
               favoritesViewModel: FavoritesViewModel)
{

    NavHost(
        navController = navController,
        startDestination = BottomBarNavigationItems.Team.path,
        modifier = modifier
    ) {
        composable(BottomBarNavigationItems.Team.path){ TeamScreen( teamViewModel, favoritesViewModel) }

        composable(BottomBarNavigationItems.Squad.path) { SquadScreen(navController, teamViewModel) }

        composable("SearchScreen"){ SearchScreen(navController, teamViewModel) }

        composable("playerDetail/{id}")
        { backStackEntry ->
            val index = backStackEntry.arguments?.getString("id")
            PlayerScreen(index)
        }

        composable(BottomBarNavigationItems.Favorites.path){ FavoritesScreen(navController, favoritesViewModel,teamViewModel) }
    }
}