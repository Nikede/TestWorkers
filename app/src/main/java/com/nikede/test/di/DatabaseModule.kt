package com.nikede.test.di

import android.content.Context
import com.nikede.test.data.db.SpecialtiesDao
import com.nikede.test.data.db.TestDatabase
import com.nikede.test.data.db.WorkersDao
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule(val context: Context) {

    @Provides
    fun provideTestDatabase(): TestDatabase {
        return TestDatabase(context)
    }

    @Provides
    fun provideWorkersDao(testDatabase: TestDatabase): WorkersDao {
        return testDatabase.workersDao()
    }

    @Provides
    fun provideSpecialtiesDao(testDatabase: TestDatabase): SpecialtiesDao {
        return testDatabase.specialtiesDao()
    }

}