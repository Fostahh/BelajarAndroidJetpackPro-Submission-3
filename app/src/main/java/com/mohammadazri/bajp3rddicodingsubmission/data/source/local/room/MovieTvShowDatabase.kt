package com.mohammadazri.bajp3rddicodingsubmission.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mohammadazri.bajp3rddicodingsubmission.data.source.local.entity.MovieEntity
import com.mohammadazri.bajp3rddicodingsubmission.data.source.local.entity.TvShowEntity

@Database(entities = [MovieEntity::class, TvShowEntity::class], version = 1, exportSchema = false)
abstract class MovieTvShowDatabase : RoomDatabase() {

    abstract fun movieTvShowDao(): MovieTvShowDao

    companion object {
        @Volatile
        private var INSTANCE: MovieTvShowDatabase? = null

        @JvmStatic
        fun getInstance(context: Context): MovieTvShowDatabase {
            if (INSTANCE == null) {
                synchronized(MovieTvShowDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        MovieTvShowDatabase::class.java, "Film.db"
                    )
                        .build()
                }
            }
            return INSTANCE as MovieTvShowDatabase
        }
    }
}