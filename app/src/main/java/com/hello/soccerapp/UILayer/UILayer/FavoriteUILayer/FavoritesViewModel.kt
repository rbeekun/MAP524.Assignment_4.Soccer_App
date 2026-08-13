package com.hello.soccerapp.UILayer.UILayer.FavoriteUILayer

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hello.soccerapp.datalayer.DataBaseLayer.DataBaseRepo
import com.hello.soccerapp.datalayer.DataBaseLayer.Team
import com.hello.soccerapp.datalayer.DataClass.TeamSearchResult
import com.hello.soccerapp.datalayer.SearchDataLayer.SearchRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class FavoritesViewModel(application : Application, repo: DataBaseRepo) : AndroidViewModel(application)
{
    private val dataBaseRepo = repo
    private var _favoriteTeams = MutableStateFlow<List<Team>>(emptyList())
    val favoriteTeams : StateFlow<List<Team>> = _favoriteTeams

    private val searchRepo = SearchRepo()

    fun addTeamToDB(team : Team)
    {
        viewModelScope.launch{
            dataBaseRepo.addNewTeam(team)
        }
    }

    fun getAllTeams()
    {
        viewModelScope.launch {
            _favoriteTeams.value = dataBaseRepo.getAllTeams()
        }
    }

    fun removeTeam(team:String)
    {
        viewModelScope.launch { dataBaseRepo.deleteTeam(team) }
    }

    suspend fun findAPITeam(teamID : String?): TeamSearchResult
    {
        val response = searchRepo.getTeamById(teamID)
        return response[0]
    }

}