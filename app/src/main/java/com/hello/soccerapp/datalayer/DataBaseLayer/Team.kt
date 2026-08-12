package com.hello.soccerapp.datalayer.DataBaseLayer

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Team(
    @PrimaryKey
    val teamID : Int,

    @ColumnInfo("club_name")
    val name : String,
    @ColumnInfo("club_logo")
    val logo : String
)
