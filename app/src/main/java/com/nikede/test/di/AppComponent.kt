package com.nikede.test.di

import com.nikede.test.ui.specialties.SpecialtiesListFragment
import com.nikede.test.ui.workers.list.WorkersListFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [DatabaseModule::class, NetworkModule::class, ViewModelFactoriesModule::class])
@Singleton
interface AppComponent {
    fun inject(fragment: SpecialtiesListFragment)
    fun inject(fragment: WorkersListFragment)
}