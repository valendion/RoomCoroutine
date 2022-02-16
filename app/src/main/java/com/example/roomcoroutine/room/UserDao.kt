package com.example.kalvin_iring_allo_172094.room

import androidx.room.*
import com.example.roomcoroutine.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addUser(user: User)

    @Update
    fun updateUser(user: User)

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM User")
    fun getAllUser(): List<User>

    @Query("SELECT * FROM User WHERE nis=:nisUser ")
    fun getUser(nisUser: String): User
}