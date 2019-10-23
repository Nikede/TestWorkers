package com.nikede.test.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nikede.test.data.db.entities.Specialty
import com.nikede.test.data.db.entities.Worker

@Database(
    entities = [Specialty::class, Worker::class],
    version = 1
)
abstract class TestDatabase: RoomDatabase() {
    abstract fun workersDao(): WorkersDao
    abstract fun specialtiesDao(): SpecialtiesDao

    companion object {
        @Volatile private var instance: TestDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                TestDatabase::class.java, "test.db")
                .build()
    }
}