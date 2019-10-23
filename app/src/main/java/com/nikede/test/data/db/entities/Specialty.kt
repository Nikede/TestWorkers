package com.nikede.test.data.db.entities


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "specialities")
data class Specialty(
    val name: String,
    @SerializedName("specialty_id")
    @PrimaryKey(autoGenerate = false)
    val specialtyId: Int
)