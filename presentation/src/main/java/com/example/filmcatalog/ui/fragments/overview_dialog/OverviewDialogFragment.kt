package com.example.filmcatalog.ui.fragments.overview_dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.filmcatalog.R
import com.example.filmcatalog.databinding.OverviewDialogBinding

class OverviewDialogFragment : DialogFragment() {

    private lateinit var _binding: OverviewDialogBinding
    private val binding
        get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = OverviewDialogBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val overview = arguments?.getString("overview", "")
        binding.overviewDialogText.text = overview
    }
}