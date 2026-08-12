package com.hello.soccerapp.datalayer.DataClass

data class TeamApiResponse(
    val response: List<TeamSearchResult>
)

data class TeamSearchResult(
    val team : TeamInfo,
    val venue : VenueInfo,
)

data class TeamInfo(
    val id : String,
    val name : String,
    val country : String,
    val founded : Int,
    val logo : String
)

data class VenueInfo(
    val id : Int,
    val name : String,
    val address : String,
    val city : String,
    val capacity : Int,
    val surface : String,
    val image : String
)

