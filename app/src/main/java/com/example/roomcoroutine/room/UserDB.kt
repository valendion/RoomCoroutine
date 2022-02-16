package com.example.kalvin_iring_allo_172094.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomcoroutine.model.User

@Database(entities = [User::class], version = 1)

abstract class UserDB : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {

        @Volatile private var instance: UserDB? = null
        private val LOCK = Any()


        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }

        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            UserDB::class.java,
            "user321.db"
        ).build()

    }
}