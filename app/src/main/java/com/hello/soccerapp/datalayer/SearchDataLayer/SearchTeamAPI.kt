package com.hello.soccerapp.datalayer.SearchDataLayer

import com.hello.soccerapp.datalayer.DataClass.PlayerAPIResponse
import com.hello.soccerapp.datalayer.DataClass.SquadAPIResponse
import com.hello.soccerapp.datalayer.DataClass.TeamApiResponse
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface FootballAPI{
    @GET("/teams")
    suspend fun autoCompleteTeam(
        @Query(value = "search") query : String
    ): TeamApiResponse

    @GET("/players/squads")
    suspend fun findSquad(
        @Query(value = "team") query: String?
    ): SquadAPIResponse

    @GET("players/profiles")
    suspend fun findPlayer(
        @Query(value = "player") query: String?
    ): PlayerAPIResponse

    @GET("/teams")
    suspend fun findTeamById(
        @Query(value = "id") query : String?
    ): TeamApiResponse
}

object SearchAPIService{
    private const val BASE_URL = "https://v3.football.api-sports.io/"
    private const val API_KEY = "d3713561971ba527c24ebcbaab721ac8"

    private val client = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("x-apisports-key", API_KEY)
                .build()
            chain.proceed(request)
        }
        .build()

    val api: FootballAPI by lazy{
        Retrofit.Builder().baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(FootballAPI::class.java)
    }
}