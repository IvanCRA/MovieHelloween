package com.example.moviehelloween.data

import android.content.Context
import java.io.File

class Cache(private val context: Context) {
    fun saveHistory(search: List<String>, filename: String) {
        val file = File(context.filesDir, filename)
        file.bufferedWriter().use { writer ->
            for (item in search) {
                writer.write("$item\n")
            }
        }

    }

    fun readHistory(filename: String): List<String> {
        val file = File(context.filesDir, filename)
        if (file.exists())
            return file.readLines()
        else
            return emptyList()
    }
}
