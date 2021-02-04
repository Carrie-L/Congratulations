package com.carrie.congratulations.data.dao

import android.content.Context
import android.util.Log
import androidx.room.*

/**
 * Created by Carrie on 2021/2/3.
 */
const val DB_VERSION = 1

@Database(entities = [Habit::class],version = DB_VERSION,exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun habitDao():HabitDao


    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private const val TAG = "AppDatabase"

        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                Log.i(TAG, "database instance = null")
            } else {
                Log.i(TAG, "database instance != null")
            }
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            Log.i(TAG, "buildDatabase  ")
            return Room.databaseBuilder(context, AppDatabase::class.java, "goodhabits.db")
                .fallbackToDestructiveMigration()   // 告诉Room，在找不到迁移规则时，可以破坏性重建数据库，注意这会删除所有数据库表数据。
//                .addMigrations(MIGRATION_4_5, MIGRATION_5_6, MIGRATION_6_7, MIGRATION_7_8, MIGRATION_8_9,MIGRATION_9_10)  // , MIGRATION_6_7
                .build()
        }
    }
}