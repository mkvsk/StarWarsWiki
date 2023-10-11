package com.example.starwarswiki.core.converter

import androidx.room.TypeConverter

object ListConverter {
    @TypeConverter
    fun toString(list: List<String>?): String {
        return list?.joinToString("|") ?: ""
    }

    @TypeConverter
    fun toList(string: String?): List<String> {
        return if (string == null) {
            emptyList()
        } else {
            (string.split("|").toList())
        }
    }
}