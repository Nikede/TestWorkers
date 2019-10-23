package com.nikede.test.data.repository

import androidx.lifecycle.LiveData
import com.nikede.test.data.db.entities.Specialty
import com.nikede.test.data.db.entities.Worker

interface TestRepository {
    suspend fun getSpecialties(): LiveData<List<Specialty>>
    suspend fun getWorkers(specialtyId: Int): LiveData<List<Worker>>
}