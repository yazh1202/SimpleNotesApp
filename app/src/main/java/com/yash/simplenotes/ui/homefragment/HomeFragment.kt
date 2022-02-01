package com.yash.simplenotes.ui.homefragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.yash.simplenotes.R
import com.yash.simplenotes.database.Date
import com.yash.simplenotes.databinding.FragmentHomeBinding
import com.yash.simplenotes.viewmodels.HomeViewModel

const val DTAG = "HOMEFRAGMENT"

class HomeFragment : Fragment() {
    val viewModel: HomeViewModel by activityViewModels()
    private lateinit var _binding: FragmentHomeBinding
    val binding: FragmentHomeBinding
        get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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

    val moveToDetails: (String, String) -> Unit = { s: String, s1: String ->
        val bundle = bundleOf("Title" to s, "Text" to s1)
        findNavController().navigate(R.id.action_homeFragment_to_detailsFragment, bundle)
    }

}