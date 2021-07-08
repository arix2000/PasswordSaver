package com.password.saver.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.password.saver.database.dao.PasswordDao
import com.password.saver.models.Password

@Database(entities = [Password::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract val passwordDao: PasswordDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this)
            {
                var instance = INSTANCE
                if (instance == null) {

                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java, "appDatabase.db")
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }

                return instance
            }

        }
    }
}