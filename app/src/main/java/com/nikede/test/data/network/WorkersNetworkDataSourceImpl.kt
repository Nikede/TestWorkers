package com.nikede.test.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nikede.test.data.network.response.WorkersResponse
import com.nikede.test.internal.NoConnectivityException

class WorkersNetworkDataSourceImpl(
    private val workersApiService: WorkersApiService
) : WorkersNetworkDataSource {

    private val _downloadedWorkers = MutableLiveData<WorkersResponse>()

    override val downloadedWorkers: LiveData<WorkersResponse>
        get() = _downloadedWorkers

    override suspend fun fetchWorkers() {
        try {
            _downloadedWorkers.postValue(
                workersApiService
                    .getWorkers()
                    .await()
            )
        } catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection", e)
        }
    }
}