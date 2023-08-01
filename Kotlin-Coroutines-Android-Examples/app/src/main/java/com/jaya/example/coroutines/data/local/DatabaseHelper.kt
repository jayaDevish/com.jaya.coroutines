package com.jaya.example.coroutines.data.local

import com.jaya.example.coroutines.data.local.entity.User

interface DatabaseHelper {

    suspend fun getUsers(): List<User>

    suspend fun insertAll(users: List<User>)

}