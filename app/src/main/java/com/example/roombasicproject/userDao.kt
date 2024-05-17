package com.example.roombasicproject

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.example.roombasicproject.entities.User

@Dao
interface userDao {

    @Upsert
    suspend fun upsertUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("DELETE FROM users_table")
    suspend fun deleteAllUser()


    @Query("SELECT * FROM users_table ORDER BY id ASC")
    fun getAllUserData(): LiveData<List<User>>
}