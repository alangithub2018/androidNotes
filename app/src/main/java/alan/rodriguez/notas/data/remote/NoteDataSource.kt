package alan.rodriguez.notas.data.remote

import alan.rodriguez.notas.data.model.Note
import alan.rodriguez.notas.data.model.NoteList

class NoteDataSource(private val apiService: ApiService) {

    suspend fun getNotes():NoteList = apiService.getNotes()

    suspend fun saveNote(note:Note?):Note?{
        apiService.saveNote(note)
        return note
    }
}