package com.nikede.test.di

import android.content.Context
import com.nikede.test.data.db.SpecialtiesDao
import com.nikede.test.data.db.TestDatabase
import com.nikede.test.data.db.WorkersDao
import com.nikede.test.data.network.*
import com.nikede.test.data.repository.TestRepository
import com.nikede.test.data.repository.TestRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class NetworkModule(val context: Context) {

    @Provides
    fun provideConnectivityInterceptor(): ConnectivityInterceptor {
        return ConnectivityInterceptorImpl(context)
    }

    @Provides
    fun provideWorkersApiService(connectivityInterceptor: ConnectivityInterceptor): WorkersApiService {
        return WorkersApiService(connectivityInterceptor)
    }

    @Provides
    fun provideWorkersNetworkDataSource(workersApiService: WorkersApiService): WorkersNetworkDataSource {
        return WorkersNetworkDataSourceImpl(workersApiService)
    }

    @Provides
    fun provideTestRepository(specialtiesDao: SpecialtiesDao, workersDao: WorkersDao, workersNetworkDataSource: WorkersNetworkDataSource): TestRepository {
        return TestRepositoryImpl(specialtiesDao, workersDao, workersNetworkDataSource)
    }
}