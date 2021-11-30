package com.example.filmcatalog.ui.base

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.utils.ToastUtils


abstract class BaseFragment : Fragment() {


    protected val alertDialog = BaseAlertDialog("Loading...")
//    protected var binding: T? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        parent: ViewGroup?,
//        savedInstanseState: Bundle?
//    ): View? {
//        binding = DataBindingUtil.inflate(
//            inflater,
//            provideViewId(),
//            parent,
//            false
//        )
//
//        return binding?.root
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//
//        binding = null
//    }
//
//    abstract fun provideViewId(): Int

    fun showError(message: String) {

        ToastUtils.showShortMessage(context, message)
    }

    fun showLoading() {

        alertDialog.show(provideFragmentManager(), null)
    }

    fun hideLoading() {
        alertDialog.dismiss()
    }

    fun showMessage(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    abstract fun provideFragmentManager(): FragmentManager
}