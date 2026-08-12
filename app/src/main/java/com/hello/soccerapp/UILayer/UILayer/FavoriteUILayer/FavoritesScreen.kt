package com.hello.soccerapp.UILayer.UILayer.FavoriteUILayer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.navigation.NavController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hello.soccerapp.UILayer.UILayer.TeamUILayer.TeamViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@Composable
fun FavoritesScreen(navController: NavController,
                    favoritesViewModel: FavoritesViewModel,
                    teamViewModel: TeamViewModel)
{
    val teamsDB by favoritesViewModel.favoriteTeams.collectAsState()
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = Unit) { favoritesViewModel.getAllTeams() }

    Column(modifier = Modifier.fillMaxWidth()){
        LazyColumn {
            items(teamsDB)
            {
                team ->
                Card(modifier = Modifier.fillMaxWidth().padding(10.dp)
                    .clickable(onClick = {
                        scope.launch {
                            val result = favoritesViewModel.findAPITeam(team.teamID.toString())
                            teamViewModel.selectTeam(result)
                            navController.navigate("main")}
                    }))
                {
                    Row(verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(10.dp))
                    {
                        AsyncImage(
                            model = team.logo,
                            contentDescription = "${team.name} logo",
                            modifier = Modifier.padding(start = 5.dp)
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(modifier = Modifier.padding(16.dp))
                        {
                            Text(fontSize = 25.sp,
                                text = "${team.name}")
                        }
                    }
                }
            }
        }
    }
}