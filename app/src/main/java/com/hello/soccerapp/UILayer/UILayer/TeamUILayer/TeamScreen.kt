package com.hello.soccerapp.UILayer.UILayer.TeamUILayer

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.hello.soccerapp.UILayer.UILayer.FavoriteUILayer.FavoritesViewModel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.rememberCoroutineScope
import com.hello.soccerapp.datalayer.DataBaseLayer.Team
import kotlinx.coroutines.launch

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TeamScreen(teamViewModel: TeamViewModel,
               favoritesViewModel: FavoritesViewModel)
{

    val selected = teamViewModel.selectedTeam

    // boolean variable to check if current selected team is in database
    var favorite by remember { mutableStateOf(false) }
    var showAlertDialog by remember { mutableStateOf(false) }

    val teamsDB by favoritesViewModel.favoriteTeams.collectAsState()
    
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutine = rememberCoroutineScope()

    // isFavorite is called each time selected and teamsDB is changed
    LaunchedEffect(selected, teamsDB) {
        favorite = isFavorite(selected?.team?.name, teamsDB)
    }

    LaunchedEffect(Unit) {
        favoritesViewModel.getAllTeams()
    }

    if (selected == null) {
        Column(
            modifier = Modifier.fillMaxSize().padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Card(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "No team selected. Tap the search icon to find a team.",
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    textAlign = TextAlign.Center
                )
            }
        }
        return
    }

    val team = selected.team
    val venue = selected.venue

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // ---- Hero header: big logo + name + code ----
            item {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Box(modifier = Modifier.fillMaxWidth()) {

                        Column(
                            modifier = Modifier.fillMaxWidth().padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            AsyncImage(
                                model = team.logo,
                                contentDescription = "${team.name} logo",
                                modifier = Modifier.size(110.dp)
                            )
                            Spacer(modifier = Modifier.height(12.dp))
                            Text(
                                text = team.name,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "${team.country}",
                                fontSize = 14.sp,
                                color = Color.Gray
                            )
                        }

                        IconButton(
                            onClick = {
                                if (favorite){ showAlertDialog = true }
                                else{
                                    var team = Team(teamID = selected.team.id.toInt(), name = selected.team.name, logo = selected.team.logo)
                                    favoritesViewModel.addTeamToDB(team)
                                    favorite = true
                                    coroutine.launch { snackbarHostState.showSnackbar("${selected.team.name} has been added to your favorites") }
                                }
                            },
                            modifier = Modifier
                                .align(Alignment.TopEnd)
                                .padding(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Star,
                                contentDescription = "Favorite team",
                                tint = if (favorite) Color.Green else Color.Gray
                            )
                        }
                    }
                }
            }

            // ---- Team info card ----
            item {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                        Text("Team Info", fontSize = 16.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.height(10.dp))
                        InfoRow(label = "Founded", value = team.founded.toString())
                        InfoRow(label = "Country", value = team.country)
                    }
                }
            }

            // ---- Venue card ----
            item {
                Card(modifier = Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        Spacer(modifier = Modifier.height(8.dp))

                        AsyncImage(
                            model = venue.image,
                            contentDescription = "${venue.name} stadium",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(160.dp)
                        )
                        Column(modifier = Modifier.fillMaxWidth().padding(16.dp)) {
                            Text(
                                text = "Venue: ${venue.name}",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            InfoRow(label = "Address", value = venue.address)
                            InfoRow(label = "City", value = venue.city)
                            InfoRow(label = "Capacity", value = "${venue.capacity.toString()} seats")
                            InfoRow(label = "Surface", value = venue.surface)
                        }
                    }
                }
            }
        }

        if(showAlertDialog)
        {
                AlertDialog(
                    onDismissRequest = {showAlertDialog = false},
                    title = { Text("${selected.team.name}") },
                    text = { Text("Do you want to remove this team from your favorites?") },
                    confirmButton =
                        {
                            TextButton( onClick =
                                {
                                    favoritesViewModel.removeTeam(selected.team.name)
                                    favorite = false
                                    showAlertDialog = false
                                    coroutine.launch { snackbarHostState.showSnackbar("${selected.team.name} has been removed from your favorites") }
                                })
                            {
                                Text("Yes")
                            }
                        },
                    dismissButton =
                        {
                            TextButton( onClick = { showAlertDialog = false })
                            { Text("No") }
                        }
                )

        }
    }
}

@Composable
fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, color = Color.Gray, fontSize = 14.sp)
        Text(text = value, fontWeight = FontWeight.Medium, fontSize = 14.sp)
    }
}
