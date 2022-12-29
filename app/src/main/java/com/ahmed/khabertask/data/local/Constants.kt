package com.ahmed.khabertask.data.local

import androidx.datastore.preferences.core.stringPreferencesKey

object Constants {

    const val BASE_URL = "http://40.127.194.127:5656/Salamtak/"

    // DataStore
    const val PREFERENCE_NAME = "app_preferences"
    val TOKEN_KEY = stringPreferencesKey(name = "user_token")
}