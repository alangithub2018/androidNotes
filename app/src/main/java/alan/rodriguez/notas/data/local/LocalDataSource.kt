package alan.rodriguez.notas.data.local

import alan.rodriguez.notas.data.model.NoteEntity
import alan.rodriguez.notas.data.model.NoteList
import alan.rodriguez.notas.data.model.toNoteList

class LocalDataSource (private val noteDao: NoteDao){
    suspend fun getNotes():NoteList = noteDao.getNotes().toNoteList()

    suspend fun saveNote(note:NoteEntity) = noteDao.saveNote(note)
}