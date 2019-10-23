package com.nikede.test.ui.specialties

import androidx.lifecycle.ViewModel
import com.nikede.test.data.repository.TestRepository
import com.nikede.test.internal.lazyDeferred

class SpecialtiesListViewModel(
    private val testRepository: TestRepository
) : ViewModel() {
    val specialties by lazyDeferred {
        testRepository.getSpecialties()
    }
}
