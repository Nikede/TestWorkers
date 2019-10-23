package com.nikede.test

import android.app.Application
import com.nikede.test.di.*

class TestApplication: Application() {
    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .databaseModule(DatabaseModule(applicationContext))
            .networkModule(NetworkModule(applicationContext))
            .viewModelFactoriesModule(ViewModelFactoriesModule())
            .build()
    }
}