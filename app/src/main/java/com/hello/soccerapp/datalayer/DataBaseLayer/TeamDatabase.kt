package com.hello.soccerapp.datalayer.DataBaseLayer

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlin.jvm.java

@Database(version = 1, entities = [Team::class])
abstract class TeamDatabase : RoomDatabase()
{
    abstract fun teamDao(): TeamDAO

    companion object{
        @Volatile
        private var INSTANCE : TeamDatabase? = null

        fun getDB(context: Application): TeamDatabase
        {
            return INSTANCE ?: synchronized(this)
            {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TeamDatabase::class.java,
                    "teamsDB").build()
                INSTANCE = instance
                instance
            }
        }
    }
}