package com.example.crudv1.navigation
import android.content.Context
import android.content.SharedPreferences

object SessionManager {
    private const val PREF_NAME = "session_pref"
    private const val KEY_LOGGED_IN = "logged_in"
    private const val KEY_USERNAME = "username"
    fun isLoggedIn(context: Context): Boolean {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(KEY_LOGGED_IN, false)
    }

    fun setLoggedIn(context: Context, loggedIn: Boolean) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean(KEY_LOGGED_IN, loggedIn)
        editor.apply()
    }
    fun setUsername(context: Context, username: String) {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString(KEY_USERNAME, username)
            apply()
        }
    }

    fun getUsername(context: Context): String {
        val sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        //return sharedPreferences.getString(KEY_USERNAME, "") ?: ""
        return sharedPreferences.getString(KEY_USERNAME, "") ?: ""
    }
}
