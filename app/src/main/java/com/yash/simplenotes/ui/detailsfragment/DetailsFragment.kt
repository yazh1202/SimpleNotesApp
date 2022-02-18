package com.yash.simplenotes.ui.detailsfragment

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
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

const val DTAG = "DETAILSFRAGMENT"

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
    /**
     * Called to do initial creation of a fragment.  This is called after
     * [.onAttach] and before
     * [.onCreateView].
     *
     *
     * Note that this can be called while the fragment's activity is
     * still in the process of being created.  As such, you can not rely
     * on things like the activity's content view hierarchy being initialized
     * at this point.  If you want to do work once the activity itself is
     * created, add a [androidx.lifecycle.LifecycleObserver] on the
     * activity's Lifecycle, removing it when it receives the
     * [Lifecycle.State.CREATED] callback.
     *
     *
     * Any restored child fragments will be created before the base
     * `Fragment.onCreate` method returns.
     *
     * @param savedInstanceState If the fragment is being re-created from
     * a previous saved state, this is the state.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        binding.apply {
            val note: NoteData? = arguments?.get("Note") as NoteData?
            if (note != null) {
                dateCreated.text = getString(R.string.datePrefix, note.date)
                noteTextDetail.setText(note.note)
                noteTitleDetail.setText(note.title)
            }
        }
        return binding.root
    }

    /**
     * Initialize the contents of the Fragment host's standard options menu.  You
     * should place your menu items in to <var>menu</var>.  For this method
     * to be called, you must have first called [.setHasOptionsMenu].  See
     * [Activity.onCreateOptionsMenu]
     * for more information.
     *
     * @param menu The options menu in which you place your items.
     *
     * @see .setHasOptionsMenu
     *
     * @see .onPrepareOptionsMenu
     *
     * @see .onOptionsItemSelected
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.details_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.deleteNote) {
            val note: NoteData? = arguments?.get("Note") as NoteData?
            if (note != null) {
                viewModel.deleteNote(note)
            }
            findNavController().navigateUp()
            return true
        }
        if(id==R.id.saveNote){
            val note: NoteData? = arguments?.get("Note") as NoteData?
            if (note != null) {
                val noteD = NoteData(
                    note.id,
                    binding.noteTitleDetail.text.toString(),
                    binding.noteTextDetail.text.toString(),
                    date = Date.getDate()
                )
                if(binding.noteTitleDetail.text.toString()==""){
                    Toast.makeText(context,"Title Cannot be empty",Toast.LENGTH_SHORT).show()
                }else {
                    viewModel.updateNote(note = noteD)
                    findNavController().navigateUp()
                }
            }

        }
        return false
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
            if(binding.noteTitleDetail.text.toString() != ""){
                viewModel.updateNote(note = noteD)
            }else{
                Toast.makeText(context, "Empty title! Note not saved", Toast.LENGTH_SHORT).show()
            }
        }
        super.onDestroyView()
    }
}