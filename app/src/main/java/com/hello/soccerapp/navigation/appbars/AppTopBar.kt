package com.hello.soccerapp.navigation.appbars

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.hello.soccerapp.UILayer.UILayer.TeamUILayer.TeamViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(navController: NavController, teamViewModel: TeamViewModel)
{
    TopAppBar(
        title = { displayTitle(teamViewModel) },
        actions = {
            IconButton(onClick = {navController.navigate("SearchScreen")}){
                Icon(Icons.Default.Search, contentDescription = "Search")
            }
        }
    )
}

@Composable
private fun displayTitle(teamViewModel : TeamViewModel)
{
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = teamViewModel.selectedTeam?.team?.logo,
            contentDescription = "${teamViewModel.selectedTeam?.team?.name} logo",
            modifier = Modifier.size(50.dp)
        )

        Spacer(modifier = Modifier.width(8.dp))

        Text(text = getTitle(teamViewModel))
    }
}

private fun getTitle(teamViewModel: TeamViewModel): String
{
    return if (teamViewModel.selectedTeam == null) { "" }
    else teamViewModel.selectedTeam?.team?.name.toString()
}