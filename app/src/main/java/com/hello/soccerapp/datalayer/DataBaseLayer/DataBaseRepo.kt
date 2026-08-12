package com.hello.soccerapp.datalayer.DataBaseLayer

class DataBaseRepo(val teamDao : TeamDAO)
{
    suspend fun addNewTeam(team : Team) {teamDao.insertNewTeam(team)}

    suspend fun getAllTeams() : List<Team> { return teamDao.getAllTeams() }

    suspend fun deleteTeam(team : String){ return teamDao.deleteTeam(team) }
}