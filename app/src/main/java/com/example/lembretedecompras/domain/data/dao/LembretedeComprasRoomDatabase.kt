package com.example.lembretedecompras.domain.data.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.lembretedecompras.domain.models.Product

@Database(
    entities = [Product::class], version = 1, exportSchema =
    false
)
abstract class LembretedeComprasRoomDatabase : RoomDatabase() {
    abstract fun productDAO(): ProductDAO

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: LembretedeComprasRoomDatabase? = null

        fun getDatabase (context: Context):
                LembretedeComprasRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context. applicationContext,
                    LembretedeComprasRoomDatabase ::class.java,
                    "lembrete_compra_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
