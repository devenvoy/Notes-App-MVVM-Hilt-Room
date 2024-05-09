package com.example.notemvvmapp.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import com.example.notemvvmapp.R
import com.example.notemvvmapp.data.model.Note
import com.example.notemvvmapp.data.viewModel.NoteViewModel
import com.example.notemvvmapp.databinding.FragmentAddNoteBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.Date



/* New Learned ->
1. MenuInflater
 */

@AndroidEntryPoint
class AddNoteFragment : Fragment(R.layout.fragment_add_note), MenuProvider {


    private var _binding: FragmentAddNoteBinding? = null
    private val binding get() = _binding!!

    private val noteViewModel: NoteViewModel by viewModels()

    private lateinit var addNoteView: View

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        addNoteView = view

    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun saveNote(view: View) {

        if (binding.addNoteTitle.text.toString().trim().isEmpty()) {
            Toast.makeText(activity, "Can't be Save If Title is Empty", Toast.LENGTH_SHORT)
                .show()
            return
        }

        val note = Note(
            id = 0,
            noteTitle = binding.addNoteTitle.text.toString().trim(),
            noteDescription = binding.addNoteDesc.text.toString().trim(),
            createDate = Date(),
            modifyDate = Date(),
        )
        noteViewModel.addNote(note)
        Toast.makeText(activity, "Saved Success", Toast.LENGTH_SHORT)
            .show()
        view.findNavController().popBackStack(R.id.homeFragment, false)
    }

    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
        menu.clear()
        menuInflater.inflate(R.menu.menu_add_note, menu)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return when (menuItem.itemId) {
            R.id.saveMenu -> {
                saveNote(addNoteView)
                true
            }
            else -> false
        }
    }


}