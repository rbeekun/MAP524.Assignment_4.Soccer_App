package com.hello.soccerapp.UILayer.UILayer.TeamUILayer

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.hello.soccerapp.datalayer.DataClass.TeamSearchResult
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class TeamViewModel: ViewModel()
{
    var selectedTeam by mutableStateOf<TeamSearchResult?>(null)
        private set

    fun selectTeam(team: TeamSearchResult?)
    {
        selectedTeam = team
    }
}