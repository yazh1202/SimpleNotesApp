package com.yash.simplenotes.ui.detailsfragment

import android.os.Bundle
import android.text.Editable
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.yash.simplenotes.R
import com.yash.simplenotes.database.Date
import com.yash.simplenotes.database.NoteData
import com.yash.simplenotes.databinding.FragmentDetailsBinding
import com.yash.simplenotes.ui.homefragment.HomeFragmentDirections
import com.yash.simplenotes.viewmodels.HomeViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass.
 * Use the [DetailsFragmen.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailsFragment : Fragment() {
    val viewModel: HomeViewModel by activityViewModels()
    private lateinit var _binding: FragmentDetailsBinding
    val binding
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        val note: NoteData? = arguments?.get("Note") as NoteData?
        binding.apply {
            if (note != null) {
                dateCreated.text = getString(R.string.datePrefix, note.date)
                noteTextDetail.setText(note.note)
                noteTitleDetail.setText(note.title)
            }

        }
        return binding.root
    }

    /**
     * Called when the view previously created by [.onCreateView] has
     * been detached from the fragment.  The next time the fragment needs
     * to be displayed, a new view will be created.  This is called
     * after [.onStop] and before [.onDestroy].  It is called
     * *regardless* of whether [.onCreateView] returned a
     * non-null view.  Internally it is called after the view's state has
     * been saved but before it has been removed from its parent.
     */
    override fun onDestroyView() {
        val note: NoteData? = arguments?.get("Note") as NoteData?
        if (note != null) {
            val noteD = NoteData(
                note.id,
                binding.noteTitleDetail.text.toString(),
                binding.noteTextDetail.text.toString(),
                date = Date.getDate()
            )
            viewModel.updateNote(note = noteD)
        }
        super.onDestroyView()
    }
}