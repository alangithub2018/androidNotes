package alan.rodriguez.notas.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import alan.rodriguez.notas.data.model.NoteEntity

@Dao
interface NoteDao {

    @Query("SELECT * FROM noteEntity")
    suspend fun getNotes():List<NoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveNote(note:NoteEntity)


}