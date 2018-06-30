package com.ban2apps.me.utils

import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.*

object MyDateUtil {

    fun getTime(timestamp: Long): String {
        val diff = (System.currentTimeMillis() - timestamp) / 1000
        return when {
            diff < 60 -> "$diff secs ago"
            diff in 60..3599 -> {
                val minutes = diff / 60
                "$minutes mins ago"
            }
            diff in 3600..86399 -> {
                val hrs = diff / 3600
                "$hrs hrs ago"
            }
            else -> {
                val date = Date(timestamp)
                SimpleDateFormat("HH:mm", Locale.getDefault()).format(date)
            }
        }
    }

    fun getDate(timestamp: Long): String = when {
        DateUtils.isToday(timestamp) -> "Today"
        isYesterday(timestamp) -> "Yesterday"
        else -> SimpleDateFormat("d MMM", Locale.getDefault()).format(Date(timestamp))
    }

    private fun isYesterday(timestamp: Long): Boolean {
        val fmt = SimpleDateFormat("d", Locale.getDefault())
        val today = fmt.format(Date(System.currentTimeMillis())).toInt()
        val pastDate = fmt.format(Date(timestamp)).toInt()
        val diff = today - pastDate
        return when (diff) {
            1 -> true
            else -> false
        }
    }

    fun isSameDate(prevStoryTime: Long, currentStoryTime: Long): Boolean {
        val fmt = SimpleDateFormat("d", Locale.getDefault())
        val recentDate = fmt.format(Date(prevStoryTime)).toInt()
        val pastDate = fmt.format(Date(currentStoryTime)).toInt()
        val diff = recentDate - pastDate
        return when (diff) {
            0 -> true
            else -> false
        }
    }
}
