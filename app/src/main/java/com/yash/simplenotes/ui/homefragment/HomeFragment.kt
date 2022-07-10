package com.yash.simplenotes.ui.homefragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.yash.simplenotes.R
import com.yash.simplenotes.database.NoteData
import com.yash.simplenotes.databinding.FragmentHomeBinding
import com.yash.simplenotes.viewmodels.HomeViewModel

const val DTAG = "HOMEFRAGMENT"

class HomeFragment : Fragment() {
    val viewModel: HomeViewModel by activityViewModels()
    private lateinit var rvAdapter: NotesRecylerViewAdapter
    private lateinit var _binding: FragmentHomeBinding
    val binding: FragmentHomeBinding
        get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    //To inflate the toolbar menu with context
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
        //SearchView implementation
        //In xml use searchview widget class not others, it doesn't work somehow
        val search = menu.findItem(R.id.search)
        val searchView = search?.actionView as? SearchView
        searchView?.setOnQueryTextListener(searchViewListener)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.clear_all -> viewModel.deleteAllNotes()
        }
        return true
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        binding.apply {
            addButton.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_addNoteFragment)
            }
            setUpRecyclerView()
        }
        viewModel.allData?.observe(viewLifecycleOwner) {
            rvAdapter.setData(it)
        }
        return binding.root
    }

    private val moveToDetails: (NoteData?) -> Unit = { noteD: NoteData? ->
        val action =
            HomeFragmentDirections.actionHomeFragmentToDetailsFragment().apply { note = noteD }
        findNavController().navigate(action)
    }

    //Listener for searchView using anonymous class
    //BUG Resolved:- Imported different type of SearchView Class than one specified at
    //layout file
    private val searchViewListener: SearchView.OnQueryTextListener =
        object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    searchDatabase(query)
                    return true
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let {
                    searchDatabase(newText)
                    return true
                }
                return false
            }
        }

    //Function to ask viewmodel to perform the search
    private fun searchDatabase(query: String) {
        val searchQuery = "%$query%"
        viewModel.apply {
            searchQuery(searchQuery)
            searchData?.observe(viewLifecycleOwner) { list ->
                list?.let {
                    rvAdapter.setData(it)
                    Log.d("HOMEFRAGMENT", searchData?.value.toString())
                }
            }
        }
    }

    //Function to set up recyclerView
    private fun setUpRecyclerView() {
        val adapter = NotesRecylerViewAdapter(moveToDetails)
        rvAdapter = adapter
        binding.apply {
            notesList.layoutManager = LinearLayoutManager(requireContext())
            notesList.adapter = rvAdapter
        }
    }
}