package com.jetpack.recipes.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Record::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recordDao(): RecordDao

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private const val DB_NAME = "records.db"

        fun getInstance(context: Context): AppDatabase? {
            if (instance == null) {
                synchronized(AppDatabase::class.java) {
                    if (instance == null) {
                        instance = createInstance(context)
                    }
                }
            }
            return instance
        }

        private fun createInstance(context: Context): AppDatabase {
            return Room.databaseBuilder(context.applicationContext,
                AppDatabase::class.java,
                DB_NAME)
                .allowMainThreadQueries()
                .build()
        }
    }
}