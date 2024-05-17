package com.example.roombasicproject

import androidx.lifecycle.LiveData
import com.example.roombasicproject.entities.User

/* a repository class abstracts access to multiple data source
* Do not have to but is suggested */

class UserRepository(private val userDao: userDao) {
    val readAllDatabase: LiveData<List<User>> = userDao.getAllUserData()

    suspend fun upsertUser(user: User) {
        userDao.upsertUser(user)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }
    suspend fun deleteUser(user: User) {
        userDao.deleteUser(user)
    }

    suspend fun deleteAllUser() {
        userDao.deleteAllUser()
    }

}