package com.nikede.test.ui.specialties

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nikede.test.data.repository.TestRepository

class SpecialtiesListViewModelFactory(
    private val testRepository: TestRepository
    ) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SpecialtiesListViewModel(testRepository) as T
    }
}