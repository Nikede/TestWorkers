package com.nikede.test.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nikede.test.data.db.entities.Specialty

@Dao
interface SpecialtiesDao {

    @Query(value = "delete from specialities")
    fun clear()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(specialty: Specialty)

    @Query(value = "select * from specialities")
    fun getSpecialties(): LiveData<List<Specialty>>
}