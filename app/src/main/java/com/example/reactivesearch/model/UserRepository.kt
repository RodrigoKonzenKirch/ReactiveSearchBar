package com.example.reactivesearch.model

import com.example.reactivesearch.data.User

interface UserRepository {
    suspend fun searchUsers(query: String): List<User>
}
