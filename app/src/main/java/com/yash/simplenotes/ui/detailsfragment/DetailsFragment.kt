package com.yash.simplenotes.ui.detailsfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.yash.simplenotes.R
import com.yash.simplenotes.database.NoteData
import com.yash.simplenotes.databinding.FragmentDetailsBinding
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        val titleText = arguments?.getString("Title")
        val mainText = arguments?.getString("Text")
        binding.apply {
            showText.text = mainText
            showTitle.text = titleText
        }
        return binding.root
    }

}