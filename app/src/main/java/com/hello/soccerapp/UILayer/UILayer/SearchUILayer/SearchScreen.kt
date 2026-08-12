package com.hello.soccerapp.UILayer.UILayer.SearchUILayer

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import androidx.compose.material3.Card
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.hello.soccerapp.UILayer.UILayer.TeamUILayer.TeamViewModel

@Composable
fun SearchScreen(navController: NavController,
                 teamViewModel: TeamViewModel,
                 searchViewModel: SearchViewModel = viewModel())
{
    var query by remember { mutableStateOf("") }

    val teams by searchViewModel.teams.collectAsState()

    Column(modifier = Modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth().padding(20.dp), horizontalArrangement = Arrangement.SpaceBetween)
        {
            OutlinedTextField(
                value = query,
                onValueChange = { query = it },
                label = { Text("Search team") }
            )

            // Call searching function in search view model and pass query as argument
            Button( onClick = { searchViewModel.searching(query) } )
            {Icon(Icons.Default.Search, contentDescription = "Search") }
        }

        LazyColumn {
            items(teams)
            {
                team ->
                // When card is clicked, function selectTeam in teamViewModel is
                // called. Pass TeamSearchResult object is passed as argument
                // and navigate to main screen
                Card(modifier = Modifier.fillMaxWidth().padding(10.dp)
                    .clickable(onClick = {
                        teamViewModel.selectTeam(team)
                        navController.navigate("main")
                    }))
                {

                    Row(
                        verticalAlignment = Alignment.CenterVertically)
                    {
                        AsyncImage(
                            model = team.team.logo,
                            contentDescription = "${team.team.name} logo",
                            modifier = Modifier.padding(start = 5.dp)
                        )

                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(fontSize = 25.sp,
                                text = "${team.team.name}")
                            Text("${team.team.country}")
                        }
                    }
                }
            }
        }
    }
}

@SuppressLint("ViewModelConstructorInComposable")
@Preview(showBackground = true)
@Composable
fun SearchScreenPreview() {
    SearchScreen(
        navController = rememberNavController(),
        teamViewModel = TeamViewModel(),
        searchViewModel = SearchViewModel()
    )
}