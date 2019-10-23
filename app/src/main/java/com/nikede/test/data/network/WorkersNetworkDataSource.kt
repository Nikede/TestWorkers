package com.nikede.test.data.network

import androidx.lifecycle.LiveData
import com.nikede.test.data.network.response.WorkersResponse

interface WorkersNetworkDataSource {
    val downloadedWorkers: LiveData<WorkersResponse>

    suspend fun fetchWorkers()
}