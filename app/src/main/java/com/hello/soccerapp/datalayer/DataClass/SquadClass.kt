package com.hello.soccerapp.datalayer.DataClass

data class SquadAPIResponse(
    val response : List<SquadSearchResult>
)

data class SquadSearchResult(
    val players : List<PlayerInfo>
)

data class PlayerInfo(
    val id : String,
    val name : String,
    val number : Int,
    val position : String,
    val photo : String
)