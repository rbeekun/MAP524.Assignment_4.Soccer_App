package com.hello.soccerapp.UILayer.UILayer.SquadUILayer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hello.soccerapp.datalayer.DataClass.PlayerInfo
import com.hello.soccerapp.datalayer.SearchDataLayer.SearchRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SquadViewModel : ViewModel()
{
    private val searchRepo = SearchRepo()

    private val _players = MutableStateFlow<List<PlayerInfo>>(emptyList())
    val players : StateFlow<List<PlayerInfo>> = _players

    fun loadSquad(teamID: String?)
    {
        viewModelScope.launch {
            val response = searchRepo.getSquad(teamID)

            if (response.isNotEmpty())
            {
                _players.value = response[0].players
            }
            else _players.value = emptyList ()
        }
    }
}