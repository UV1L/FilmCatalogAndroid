package com.example.filmcatalog.ui.fragments.review_dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.example.domain.auth.entities.Review
import com.example.filmcatalog.databinding.CreateReviewDialogBinding
import com.example.filmcatalog.databinding.OverviewDialogBinding
import com.example.filmcatalog.ui.fragments.reviews.ReviewsFragmentDirections
import com.example.utils.ToastUtils
import java.io.Serializable

interface OnSendListener : Serializable {

    fun onSend(reviewText: String, rating: Float)
}

class ReviewDialogFragment : DialogFragment() {

    private lateinit var _binding: CreateReviewDialogBinding
    private val binding
        get() = _binding

    private lateinit var listener: OnSendListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = CreateReviewDialogBinding.inflate(inflater, container, false)

        listener = arguments?.getSerializable("listener") as OnSendListener

        return binding.root
    }

    override fun onResume() {
        super.onResume()

        val params: ViewGroup.LayoutParams = dialog!!.window!!.attributes
        params.width = ViewGroup.LayoutParams.MATCH_PARENT
        dialog!!.window!!.attributes = params as WindowManager.LayoutParams

        setUpButtons()
    }

    private fun setUpButtons() {

        binding.reviewRating.setOnRatingBarChangeListener { ratingBar, rating, b ->
            ratingBar.rating = rating
        }

        binding.reviewRateBtn.setOnClickListener {

            if (binding.reviewText.text.toString() != "" && binding.reviewRating.rating != 0f) {
                listener.onSend(binding.reviewText.text.toString(), binding.reviewRating.rating)
            }
            else {
                ToastUtils.showShortMessage(context, "Вы не написали ревью!")
            }
        }

        binding.reviewCancelBtn.setOnClickListener {

            if (binding.reviewText.text.toString().length > 20)
                AlertDialog.Builder(requireContext())
                    .setMessage("Ваше ревью не сохранится. Вы уверены?")
                    .setPositiveButton("Да") { _, _ -> dismiss() }
                    .setNegativeButton("Нет") { _, _ -> }
                    .create()
                    .show()
            else
                dismiss()
        }
    }
}