package com.yash.simplenotes.ui.homefragment

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.yash.simplenotes.R
import com.yash.simplenotes.database.Date
import com.yash.simplenotes.database.NoteData
import com.yash.simplenotes.databinding.FragmentHomeBinding
import com.yash.simplenotes.viewmodels.HomeViewModel

const val DTAG = "HOMEFRAGMENT"

class HomeFragment : Fragment() {
    val viewModel: HomeViewModel by activityViewModels()
    private lateinit var _binding: FragmentHomeBinding
    val binding: FragmentHomeBinding
        get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu,menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search -> viewModel.deleteAllNotes()
        }
        return true
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        val adapter = NotesRecylerViewAdapter(moveToDetails)
        binding.apply {
            addButton.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_addNoteFragment)
            }
            notesList.layoutManager = LinearLayoutManager(requireContext())
            notesList.adapter = adapter
        }
        viewModel.allData?.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
        Log.d(DTAG, Date.getDate())
        return binding.root
    }

    private val moveToDetails: (NoteData?) -> Unit = { noteD: NoteData? ->
        val action =
            HomeFragmentDirections.actionHomeFragmentToDetailsFragment().apply { note = noteD }
        val bundle = bundleOf("Note" to noteD)
        findNavController().navigate(R.id.action_homeFragment_to_detailsFragment, bundle)
    }

}