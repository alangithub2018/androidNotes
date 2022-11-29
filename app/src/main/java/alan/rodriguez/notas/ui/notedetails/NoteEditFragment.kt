package alan.rodriguez.notas.ui.notedetails

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import alan.rodriguez.notas.data.local.LocalDataSource
import alan.rodriguez.notas.data.model.Note
import alan.rodriguez.notas.data.remote.ApiClient
import alan.rodriguez.notas.data.remote.NoteDataSource
import alan.rodriguez.notas.presentation.NoteViewModel
import alan.rodriguez.notas.presentation.NoteViewModelFactory
import alan.rodriguez.notas.repository.NoteRepositoryImp
import alan.rodriguez.notas.R
import alan.rodriguez.notas.databinding.FragmentNoteEditBinding

class NoteEditFragment : Fragment(R.layout.fragment_note_edit) {

    private lateinit var binding: FragmentNoteEditBinding

    private val viewModel by viewModels<NoteViewModel>{
        NoteViewModelFactory(
            NoteRepositoryImp(
            LocalDataSource(alan.rodriguez.notas.data.local.AppDatabase.getDatabase(this.requireContext()).noteDao()),
            NoteDataSource(ApiClient.service)
        ))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentNoteEditBinding.bind(view)

        binding.btnAddNote.setOnClickListener { view->
            val titleNote: String = binding.editTitle.text.toString()
            val commentForNote: String = binding.editContent.text.toString()
            val imageProfile: String = binding.editImageUrl.text.toString()
            val addNote = Note("", titleNote, commentForNote, imageProfile)

            viewModel.saveNotes(addNote).observe(viewLifecycleOwner) { result ->
                when (result) {
                    is alan.rodriguez.notas.core.Resource.Loading -> {

                    }
                    is alan.rodriguez.notas.core.Resource.Success -> {
                        Toast.makeText(context, "Nota guardada", Toast.LENGTH_SHORT).show()
                    }
                    is alan.rodriguez.notas.core.Resource.Failure -> {
                        Toast.makeText(context, "Error al agregar la nota", Toast.LENGTH_SHORT).show()

                    }
                }
            }
        }
    }
}