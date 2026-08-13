package com.hello.soccerapp.UILayer.UILayer.SearchUILayer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hello.soccerapp.datalayer.SearchDataLayer.SearchRepo
import com.hello.soccerapp.datalayer.DataClass.TeamSearchResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel()
{
    private val searchRepo = SearchRepo()

    var teamsList = emptyList<TeamSearchResult>()
    private val _teams = MutableStateFlow<List<TeamSearchResult>>(teamsList)
    val teams : StateFlow<List<TeamSearchResult>> = _teams

    fun searching(query : String)
    {
        viewModelScope.launch {
            val result = searchRepo.searchTeam(query)
            _teams.value = result
        }
    }
}