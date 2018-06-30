package com.ban2apps.me.utils

import android.content.Context

object Prefs {

    private const val loggedIn = "isLoggedIn"
    private const val userId = "userId"
    private const val tokenId = "tokenId"
    private const val prefs = "prefs"

    fun getId(context: Context): Int {
        val pref = context.applicationContext.getSharedPreferences(prefs, 0)
        return pref.getInt(userId, 0)
    }

    fun setId(context: Context, id: Int) {
        val pref = context.applicationContext.getSharedPreferences(prefs, 0)
        val editor = pref.edit()
        editor.putInt(userId, id)
        editor.apply()
    }

    fun getTokenId(context: Context): Int {
        val pref = context.applicationContext.getSharedPreferences(prefs, 0)
        return pref.getInt(tokenId, 0)
    }

    fun setTokenId(context: Context, id: Int) {
        val pref = context.applicationContext.getSharedPreferences(prefs, 0)
        val editor = pref.edit()
        editor.putInt(tokenId, id)
        editor.putBoolean(loggedIn, true)
        editor.apply()
    }

    fun loggedIn(context: Context): Boolean {
        val pref = context.applicationContext.getSharedPreferences(prefs, 0)
        return pref.getBoolean(loggedIn, false)
    }

    fun logout(context: Context) {
        val pref = context.applicationContext.getSharedPreferences(prefs, 0)
        val editor = pref.edit()
        editor.putInt(userId, 0)
        editor.putInt(tokenId, 0)
        editor.putBoolean(loggedIn, false)
        editor.apply()
    }
}