package com.example.myapplication.repositories.shared_preferences

interface SharedPreferencesRepository {
    var accessToken: String?
    var refreshToken: String?
}