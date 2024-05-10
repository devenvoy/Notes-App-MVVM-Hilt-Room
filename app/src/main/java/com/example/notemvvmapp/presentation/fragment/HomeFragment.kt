package com.example.notemvvmapp.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notemvvmapp.R
import com.example.notemvvmapp.data.adapter.NoteAdapter
import com.example.notemvvmapp.data.model.Note
import com.example.notemvvmapp.data.viewModel.NoteViewModel
import com.example.notemvvmapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint


/* New Learned ->
1. SearchView.OnQueryTextListener Implementation for action bar searchView
2. MenuInflater Implementation
 */

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home),
    SearchView.OnQueryTextListener, MenuProvider {

    private lateinit var rvAdapter: NoteAdapter
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val noteViewModel: NoteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireActivity()
        menuHost.addMenuProvider(this, viewLifecycleOwner, Lifecycle.State.RESUMED)

        setUpHomeRecyclerView()

        // fab click
        binding.addNote.setOnClickListener {
            it.findNavController().navigate(
                R.id.action_homeFragment_to_addNoteFragment
            )
        }
    }

    private fun updateUi(notes: List<Note>?) {
        if (notes != null) {
            if (notes.isNotEmpty()) {
                binding.emptyNotesImage.visibility = View.GONE
                binding.homeRecycleView.visibility = View.VISIBLE
            } else {
                binding.emptyNotesImage.visibility = View.VISIBLE
                binding.homeRecycleView.visibility = View.GONE
            }
        }
    }

    private fun setUpHomeRecyclerView() {
        // create adapter
        rvAdapter = NoteAdapter(activity = requireActivity())

        binding.homeRecycleView.apply {
            // setup layout manager
            layoutManager =
                StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            //
            setHasFixedSize(true)
            // set adapter
            adapter = rvAdapter
        }

        // set an observer on live data
        // update adapter data as data changes
        // and update ui
        activity?.let {
            noteViewModel.getAllNotes().observe(viewLifecycleOwner) { notes ->

                rvAdapter.differ.submitList(notes)

                updateUi(notes)
            }
        }
    }

    // dose nothing on submit
    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    // filter list as user type
    override fun onQueryTextChange(newText: String?): Boolean {
        if (newText != null) {
            searchNote(newText)
        }
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    // Here we Initialise menu
    override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {

        menu.clear()
        menuInflater.inflate(R.menu.home_menu, menu)

        // here when clicked it switch from icon to search view
        val menuSearch = menu.findItem(R.id.searchMenu).actionView as SearchView
        menuSearch.isSubmitButtonEnabled = true
        menuSearch.setOnQueryTextListener(this)
    }

    override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
        return false
    }

    private fun searchNote(query: String?) {
        val searchQuery = "%$query"

        noteViewModel.searchNote(searchQuery).observe(this) { notes ->

            rvAdapter.differ.submitList(notes)

        }
    }
}

