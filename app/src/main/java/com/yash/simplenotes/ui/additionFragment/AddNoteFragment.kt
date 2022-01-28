package com.yash.simplenotes.ui.additionFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import com.google.android.material.snackbar.Snackbar
import com.yash.simplenotes.R
import com.yash.simplenotes.database.NoteData
import com.yash.simplenotes.databinding.FragmentAddNoteBinding
import com.yash.simplenotes.viewmodels.HomeViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [AddNoteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AddNoteFragment : Fragment() {
    val viewModel: HomeViewModel by activityViewModels()
    private lateinit var _binding: FragmentAddNoteBinding
    val binding
        get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_note, container, false)
        binding.apply {
            saveButton.setOnClickListener {
                val title: String = NoteTitle.text.toString()
                val noteText = NoteText.text.toString()
                if (title.isNotBlank()) {
                    val note = NoteData(0, title, noteText)
                    viewModel.addNote(note)
                } else {
                    Snackbar.make(
                        binding.root,
                        "Title Field cannot be Empty",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }
        return binding.root
    }

}