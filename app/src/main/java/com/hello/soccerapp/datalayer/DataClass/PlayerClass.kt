package com.hello.soccerapp.datalayer.DataClass

import com.google.gson.annotations.SerializedName

data class PlayerAPIResponse(
    val response : List<PlayerResult>
)

data class PlayerResult(
    val player : Player
)

data class Player(
    val id : String,
    val name : String,
    @SerializedName("firstname")
    val firstName : String,
    @SerializedName("lastname")
    val lastName : String,
    val age : String,
    val nationality : String,
    val height : String,
    val number : String,
    val position : String,
    val photo : String
)