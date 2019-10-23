package com.nikede.test.data.db.entities


import android.util.Log
import androidx.room.*
import com.google.gson.annotations.SerializedName
import com.nikede.test.data.db.WorkerConverter

@Entity(tableName = "workers")
@TypeConverters(WorkerConverter::class)
data class Worker(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    @SerializedName("avatr_url")
    var avatarUrl: String? = "",
    var birthday: String? = "",
    @SerializedName("f_name")
    var fName: String = "",
    @SerializedName("l_name")
    var lName: String = "",
    @Ignore
    var specialty: List<Specialty> = listOf(),
    var specialtyIds: List<Int> = listOf()
)