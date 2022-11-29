package alan.rodriguez.notas.repository

import alan.rodriguez.notas.data.model.Note
import alan.rodriguez.notas.data.model.NoteList

interface NoteRepository {
    suspend fun getNotes():NoteList
    suspend fun saveNote(note:Note?):Note?

}