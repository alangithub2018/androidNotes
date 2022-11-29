package alan.rodriguez.notas.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import alan.rodriguez.notas.data.model.NoteEntity

@Database( entities = [NoteEntity::class], version = 1)
abstract class AppDatabase:RoomDatabase() {

    abstract fun noteDao(): alan.rodriguez.notas.data.local.NoteDao

    companion object {
        private var INSTANCE: alan.rodriguez.notas.data.local.AppDatabase? = null

        fun getDatabase(context: Context): alan.rodriguez.notas.data.local.AppDatabase {
            alan.rodriguez.notas.data.local.AppDatabase.Companion.INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                alan.rodriguez.notas.data.local.AppDatabase::class.java,
                "NotesApplication2022"
            ).build()
            return alan.rodriguez.notas.data.local.AppDatabase.Companion.INSTANCE!!
        }
        fun destroyInstance(){
            alan.rodriguez.notas.data.local.AppDatabase.Companion.INSTANCE = null
        }
    }

}