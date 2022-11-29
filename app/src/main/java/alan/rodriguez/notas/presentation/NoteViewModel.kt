package alan.rodriguez.notas.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import kotlinx.coroutines.Dispatchers
import alan.rodriguez.notas.repository.NoteRepository
import alan.rodriguez.notas.data.model.Note

class NoteViewModel(private val repository: NoteRepository):ViewModel() {

    fun fetchNotes() = liveData(Dispatchers.IO) {
        emit(alan.rodriguez.notas.core.Resource.Loading())
        try {
            emit(alan.rodriguez.notas.core.Resource.Success(repository.getNotes()))
        }
        catch (exception: Exception){
            emit(alan.rodriguez.notas.core.Resource.Failure(exception))
        }
    }

    fun saveNotes(note:Note?) = liveData (Dispatchers.IO){
        emit(alan.rodriguez.notas.core.Resource.Loading())
        try {
            emit(alan.rodriguez.notas.core.Resource.Success(repository.saveNote(note)))
        }
        catch (exception: Exception){
            emit(alan.rodriguez.notas.core.Resource.Failure(exception))
        }

    }

}

class NoteViewModelFactory(private val repository: NoteRepository): ViewModelProvider.Factory {
    override fun <T: ViewModel> create(modelClass:Class<T>):T{
        return modelClass.getConstructor(NoteRepository::class.java).newInstance(repository)
    }
}