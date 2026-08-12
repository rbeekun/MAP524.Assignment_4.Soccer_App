package com.hello.soccerapp.UILayer.UILayer.TeamUILayer

import com.hello.soccerapp.datalayer.DataBaseLayer.Team

// Loop through database -> if selectedTeam is in database -> set favorite to true -> else set favorite to
// false
fun isFavorite(selectedTeam: String?, teamsDB: List<Team>): Boolean {
    for (team in teamsDB) {
        if (team.name == selectedTeam) {
            return true
        }
    }
    return false
}