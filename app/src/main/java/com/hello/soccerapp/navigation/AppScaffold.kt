package com.hello.soccerapp.navigation

import android.app.Application
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.compose.runtime.getValue
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.hello.soccerapp.UILayer.UILayer.FavoriteUILayer.FavoritesViewModel
import com.hello.soccerapp.UILayer.UILayer.TeamUILayer.TeamViewModel
import com.hello.soccerapp.datalayer.DataBaseLayer.DataBaseRepo
import com.hello.soccerapp.datalayer.DataBaseLayer.TeamDatabase
import com.hello.soccerapp.navigation.appbars.AppBottomBar
import com.hello.soccerapp.navigation.appbars.AppNavHost
import com.hello.soccerapp.navigation.appbars.AppTopBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScaffold()
{
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    val teamViewModel : TeamViewModel = viewModel()

    // Init instance of favScreenViewModel
    val application = LocalContext.current.applicationContext as Application
    val favoritesViewModel : FavoritesViewModel = viewModel(
        factory = viewModelFactory {
            initializer {
                val dao = TeamDatabase.getDB(application).teamDao()
                val repo = DataBaseRepo(dao)
                FavoritesViewModel(application,repo)
            }
        }
    )

    Scaffold(
        topBar = { AppTopBar(navController, teamViewModel) },

        bottomBar = { if (currentRoute != "SearchScreen") {AppBottomBar(navController, currentRoute)} }
    )
    {
        innerPadding -> AppNavHost(navController = navController,
                                    teamViewModel = teamViewModel,
                                    modifier = Modifier.padding(innerPadding),
                                    favoritesViewModel)
    }
}