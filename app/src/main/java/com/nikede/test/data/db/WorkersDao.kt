package com.nikede.test.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nikede.test.data.db.entities.Worker

@Dao
interface WorkersDao {

    @Query(value = "delete from workers")
    fun clear()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(worker: Worker)

    @Query(value = "select * from workers where specialtyIds like '%' || :specialtyId || '%'")
    fun getWorkers(specialtyId: Int): LiveData<List<Worker>>
}