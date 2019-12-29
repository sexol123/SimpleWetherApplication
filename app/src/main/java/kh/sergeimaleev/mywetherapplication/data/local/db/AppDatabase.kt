package kh.sergeimaleev.mywetherapplication.data.local.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kh.sergeimaleev.mywetherapplication.constants.LOCAL_DB_NAME
import kh.sergeimaleev.mywetherapplication.data.local.db.dao.WeatherForecastDAO
import kh.sergeimaleev.mywetherapplication.data.local.db.entities.WeatherForecastEntity
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

@Database(
    entities = [
        WeatherForecastEntity::class
    ], version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userClassDao(): WeatherForecastDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    LOCAL_DB_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return INSTANCE!!
        }
    }
}

val databaseModule = module {
    single { AppDatabase.getInstance(androidApplication()) }
}