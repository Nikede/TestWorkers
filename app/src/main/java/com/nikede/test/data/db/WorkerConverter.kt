package com.nikede.test.data.db

import android.util.Log
import androidx.room.TypeConverter
import java.lang.NumberFormatException

class WorkerConverter {

    @TypeConverter
    fun fromListInt(specialtiesIds: List<Int>): String {
        var output = ""
        specialtiesIds.forEach {
            output += "$it,"
        }
        if (output.lastIndex > 0)
            return output.substring(0, output.lastIndex)
        return output
    }

    @TypeConverter
    fun toListString(ids: String): List<Int> {
        val output = arrayListOf<Int>()
        Log.e("error", ids)
        ids.split(",").forEach {
            try {
                output.add(it.toInt())
            } catch (e: NumberFormatException) {

            }
        }
        return output
    }
}