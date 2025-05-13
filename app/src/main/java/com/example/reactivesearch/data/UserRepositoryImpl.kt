package com.example.reactivesearch.data

import com.example.reactivesearch.model.UserRepository
import kotlinx.coroutines.delay
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
//    private val apiService: ApiService
) : UserRepository {
    override suspend fun searchUsers(query: String): List<User> {

        // Simulate network call
        delay(500)
        return listOf(
            User("1", "Alice"),
            User("2", "Bob"),
            User("3", "Charlie"),
            User("4", "David"),
            User("5", "Eve"),
            User("6", "Frank"),
            User("7", "Grace"),
            User("8", "Hannah"),
            User("9", "Ivy"),
            User("10", "Jack"),
            User("11", "Katie"),
            User("12", "Liam"),
            User("13", "Mia"),
            User("14", "Noah"),
            User("15", "Olivia"),
            User("16", "Peter"),
            User("17", "Quinn"),
            User("18", "Rachel"),
            User("19", "Sam"),
            User("20", "Tina"),
            User("21", "Uma"),
            User("22", "Victor"),
            User("23", "Wendy"),
            User("24", "Xavier"),
            User("25", "Yvonne"),
            User("26", "Zoe"),
            User("27", "Aaron"),
            User("28", "Beth"),
            User("29", "Carlos"),
            User("30", "Diana"),
            User("31", "Ethan"),
            User("32", "Fiona"),
            User("33", "George"),
            User("34", "Helen"),
            User("35", "Isaac"),
            User("36", "Julia"),
            User("37", "Kevin"),
            User("38", "Laura"),
            User("39", "Michael"),
            User("40", "Nancy"),
            User("41", "Oscar"),
            User("42", "Paula"),
            User("43", "Quentin"),
            User("44", "Rose"),
            User("45", "Steve"),
            User("46", "Tiffany"),
        ).filter { it.name.contains(query, ignoreCase = true) }
    }
}