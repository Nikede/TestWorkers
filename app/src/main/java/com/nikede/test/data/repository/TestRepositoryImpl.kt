package com.nikede.test.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.nikede.test.data.db.SpecialtiesDao
import com.nikede.test.data.db.WorkersDao
import com.nikede.test.data.db.entities.Specialty
import com.nikede.test.data.db.entities.Worker
import com.nikede.test.data.network.WorkersNetworkDataSource
import com.nikede.test.data.network.response.WorkersResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TestRepositoryImpl(
    private val specialtiesDao: SpecialtiesDao,
    private val workersDao: WorkersDao,
    private val workersNetworkDataSource: WorkersNetworkDataSource
) : TestRepository {

    init {
        workersNetworkDataSource.apply {
            downloadedWorkers.observeForever { newWorkers ->
                persistFetchedWorkers(newWorkers)
            }
        }
    }

    override suspend fun getSpecialties(): LiveData<List<Specialty>> {
        return withContext(Dispatchers.IO) {
            workersNetworkDataSource.fetchWorkers()
            return@withContext specialtiesDao.getSpecialties()
        }
    }

    override suspend fun getWorkers(specialtyId: Int): LiveData<List<Worker>> {
        return withContext(Dispatchers.IO) {
            return@withContext workersDao.getWorkers(specialtyId)
        }
    }

    private fun persistFetchedWorkers(fetchedWorkers: WorkersResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            if (fetchedWorkers.workers.isNotEmpty()) {
                workersDao.clear()
                specialtiesDao.clear()
            }
            fetchedWorkers.workers.forEach{worker ->
                worker.specialtyIds = specialtyListToSpecialtyIdsList(worker.specialty)
                workersDao.upsert(worker)
                worker.specialty.forEach {specialty ->
                    specialtiesDao.upsert(specialty)
                }
            }
        }
    }

    fun specialtyListToSpecialtyIdsList(specialties: List<Specialty>): List<Int> {
        val specialtyIds = arrayListOf<Int>()
        specialties.forEach{
            specialtyIds.add(it.specialtyId)
        }
        return specialtyIds
    }
}