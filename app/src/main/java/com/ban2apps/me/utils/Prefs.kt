package com.ban2apps.me.utils

import android.content.Context

object Prefs {

    private const val userId = "userId"
    private const val tokenId = "tokenId"
    private const val prefs = "prefs"

    fun getId(context: Context): String? {
        val pref = context.applicationContext.getSharedPreferences(prefs, 0)
        return pref.getString(userId, null)
    }

    fun setId(context: Context, id: String?) {
        val pref = context.applicationContext.getSharedPreferences(prefs, 0)
        val editor = pref.edit()
        editor.putString(userId, id)
        editor.apply()
    }

    fun getTokenId(context: Context): String? {
        val pref = context.applicationContext.getSharedPreferences(prefs, 0)
        return pref.getString(tokenId, null)
    }

    fun setTokenId(context: Context, id: String?) {
        val pref = context.applicationContext.getSharedPreferences(prefs, 0)
        val editor = pref.edit()
        editor.putString(tokenId, id)
        editor.apply()
    }

    fun logout(context: Context) {
        val pref = context.applicationContext.getSharedPreferences(prefs, 0)
        val editor = pref.edit()
        editor.putString(userId, null)
        editor.putString(tokenId, null)
        editor.apply()
    }
}