package com.hello.soccerapp.datalayer.SearchDataLayer

import android.util.Log
import com.hello.soccerapp.datalayer.DataBaseLayer.Team
import com.hello.soccerapp.datalayer.DataClass.PlayerResult
import com.hello.soccerapp.datalayer.DataClass.SquadSearchResult
import com.hello.soccerapp.datalayer.DataClass.TeamSearchResult

class SearchRepo {
    suspend fun searchTeam(query : String): List<TeamSearchResult>
    {
        return SearchAPIService.api.autoCompleteTeam(query).response
    }

    suspend fun getSquad(teamID :String?): List<SquadSearchResult>
    {
        return SearchAPIService.api.findSquad(teamID).response
    }

    suspend fun getPlayer(playerID : String?): List<PlayerResult>
    {
        return SearchAPIService.api.findPlayer(playerID).response
    }

    suspend fun getTeamById(teamID : String?): List<TeamSearchResult>
    {
        return SearchAPIService.api.findTeamById(teamID).response
    }
}