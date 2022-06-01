package com.example.showcoins.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.Database
import com.example.showcoins.model.FavouriteCoin

@Database(entities = [FavouriteCoin::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun favouriteCoinDao(): FavouriteCoinDao

    companion object {
        /*
        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("")
                Log.d("AppDatabase", "The database has been update")
            }
        }
         */
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "coin_database"
                )
                    //.addMigrations()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}