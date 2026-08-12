package com.hello.soccerapp.datalayer.DataBaseLayer

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TeamDAO
{
    @Insert
    suspend fun insertNewTeam(team : Team)

    @Query("SELECT * FROM Team")
    suspend fun getAllTeams() : List<Team>

    @Query("DELETE FROM Team WHERE club_name LIKE :teamName")
    suspend fun deleteTeam(teamName : String)
}