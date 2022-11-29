package alan.rodriguez.notas.repository

import alan.rodriguez.notas.data.local.LocalDataSource
import alan.rodriguez.notas.data.model.Note
import alan.rodriguez.notas.data.model.NoteList
import alan.rodriguez.notas.data.model.toNoteEntity
import alan.rodriguez.notas.data.remote.NoteDataSource

class NoteRepositoryImp(
    private val localDataSource: LocalDataSource,
    private val dataSource: NoteDataSource):NoteRepository {

    override suspend fun getNotes(): NoteList {
        dataSource.getNotes().data.forEach{
                note -> localDataSource.saveNote(note.toNoteEntity())
        }

        return localDataSource.getNotes()
    }

    override suspend fun saveNote(note: Note?): Note? {
        return dataSource.saveNote(note)
    }
}