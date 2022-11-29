package alan.rodriguez.notas.ui.notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import alan.rodriguez.notas.data.local.LocalDataSource
import alan.rodriguez.notas.data.remote.ApiClient
import alan.rodriguez.notas.data.remote.NoteDataSource
import alan.rodriguez.notas.presentation.NoteViewModel
import alan.rodriguez.notas.presentation.NoteViewModelFactory
import alan.rodriguez.notas.repository.NoteRepositoryImp
import alan.rodriguez.notas.ui.notes.adapters.NoteAdapter
import alan.rodriguez.notas.R
import alan.rodriguez.notas.databinding.FragmentNotesBinding

class NotesFragment : Fragment(R.layout.fragment_notes) {

    private lateinit var binding: FragmentNotesBinding
    private lateinit var adapter: NoteAdapter

    private val viewModel by viewModels<NoteViewModel>{
        NoteViewModelFactory(NoteRepositoryImp(
            LocalDataSource(alan.rodriguez.notas.data.local.AppDatabase.getDatabase(this.requireContext()).noteDao()),
            NoteDataSource(ApiClient.service)
        ))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentNotesBinding.bind(view)

        binding.recyclerNotes.layoutManager = GridLayoutManager(requireContext(), 2)

        viewModel.fetchNotes().observe(viewLifecycleOwner, Observer { result ->

            when (result) {
                is alan.rodriguez.notas.core.Resource.Loading -> {
                    binding.progressbar.visibility = View.VISIBLE
                }
                is alan.rodriguez.notas.core.Resource.Success -> {
                    binding.progressbar.visibility = View.GONE

                    adapter = NoteAdapter(result.data.data) { _ ->
                        // onNoteClick(note)
                    }
                    binding.recyclerNotes.adapter = adapter
                }
                is alan.rodriguez.notas.core.Resource.Failure -> {
                    binding.progressbar.visibility = View.GONE
                }
            }
        })
        binding.btnAddNote.setOnClickListener {

            findNavController().navigate(R.id.action_notesFragment_to_noteEditFragment)
        }
    }
}
