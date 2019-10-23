package com.nikede.test.ui.workers.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nikede.test.data.repository.TestRepository

class WorkersListViewModelFactory(
    private val testRepository: TestRepository
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    var specialtyId = 0
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return WorkersListViewModel(testRepository, specialtyId) as T
    }
}