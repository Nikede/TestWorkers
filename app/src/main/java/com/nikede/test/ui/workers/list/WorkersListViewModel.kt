package com.nikede.test.ui.workers.list

import androidx.lifecycle.ViewModel
import com.nikede.test.data.repository.TestRepository
import com.nikede.test.internal.lazyDeferred

class WorkersListViewModel(
        private val testRepository: TestRepository,
        private val specialtyId: Int
) : ViewModel() {
    val workers by lazyDeferred {
        testRepository.getWorkers(specialtyId)
    }
}
