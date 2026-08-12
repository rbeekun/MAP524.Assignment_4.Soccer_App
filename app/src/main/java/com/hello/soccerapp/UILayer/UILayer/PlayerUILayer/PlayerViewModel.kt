package com.hello.soccerapp.UILayer.UILayer.PlayerUILayer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hello.soccerapp.datalayer.DataClass.Player
import com.hello.soccerapp.datalayer.SearchDataLayer.SearchRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PlayerViewModel : ViewModel()
{
    private val searchRepo = SearchRepo()

    private val _player = MutableStateFlow<Player?>(null)
    val player : StateFlow<Player?> = _player

    fun loadPlayer(playerID : String?)
    {
        viewModelScope.launch {
            val response = searchRepo.getPlayer(playerID)

            if(response.isNotEmpty())
            {
                _player.value = response[0].player
            }else _player.value = null
        }
    }
}