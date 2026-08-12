package com.hello.soccerapp.UILayer.UILayer.SquadUILayer

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.hello.soccerapp.UILayer.UILayer.TeamUILayer.TeamViewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage

@Composable
fun SquadScreen(navController: NavController,
                teamViewModel: TeamViewModel,
                squadViewModel: SquadViewModel = viewModel())
{
    val players by squadViewModel.players.collectAsState()

    if (teamViewModel.selectedTeam == null)
    {
        Column(modifier = Modifier.fillMaxSize().padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally)
        {
            Card(modifier = Modifier.fillMaxWidth())
            {
                Text(
                    text = "No team selected. Tap the search icon to find a team.",
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    textAlign = TextAlign.Center)
            }
        }
    }
    else{
        LaunchedEffect(teamViewModel.selectedTeam) {
            squadViewModel.loadSquad(teamViewModel.selectedTeam?.team?.id)
        }

        LazyColumn(
            modifier = Modifier.padding(horizontal = 12.dp),
            contentPadding = PaddingValues(vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(players) { player ->
                Card(modifier = Modifier.fillMaxWidth()) {
                    Row(
                        modifier = Modifier
                            .padding(12.dp)
                            .clickable
                            {
                                val playerID = player.id
                                navController.navigate("playerDetail/$playerID")
                            },
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        AsyncImage(
                            model = player.photo,
                            contentDescription = "${player.name} photo",
                            modifier = Modifier.size(90.dp)
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        Column {
                            Row {
                                Text("${player.number}.")
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = player.name,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                            Text(player.position)
                        }
                    }
                }
            }
        }
    }
}