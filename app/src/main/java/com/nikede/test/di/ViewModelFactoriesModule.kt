package com.nikede.test.di

import android.content.Context
import com.nikede.test.data.repository.TestRepository
import com.nikede.test.ui.specialties.SpecialtiesListViewModelFactory
import com.nikede.test.ui.workers.list.WorkersListViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ViewModelFactoriesModule {

    @Provides
    fun provideSpecialtiesViewModelFactory(testRepository: TestRepository): SpecialtiesListViewModelFactory {
        return SpecialtiesListViewModelFactory(testRepository)
    }

    @Provides
    fun provideWorkersViewModelFactory(testRepository: TestRepository): WorkersListViewModelFactory {
        return WorkersListViewModelFactory(testRepository)
    }
}