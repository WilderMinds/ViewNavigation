package com.samdev.viewnavdemo.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.samdev.viewnavdemo.R
import com.samdev.viewnavdemo.databinding.FragmentSequentialViewsBinding
import com.samdev.viewnavdemo.navigator.SequentialNavigator

class SequentialViewsFragment : Fragment() {

    // views to navigate
    private lateinit var views: List<View>

    // instance of navigator
    private lateinit var sequentialNavigator: SequentialNavigator


    private lateinit var binding: FragmentSequentialViewsBinding

    companion object {
        fun newInstance() = SequentialViewsFragment()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSequentialViewsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initSequentialNavigator()

        binding.btnNext.setOnClickListener { next() }
        binding.btnPrev.setOnClickListener { prev() }
    }


    private fun initSequentialNavigator() {
        // list of views
        views = mutableListOf(
            binding.view1,
            binding.view2,
            binding.view3,
            binding.view4,
            binding.view5,
            binding.view6
        )

        // init navigator with views
        sequentialNavigator = SequentialNavigator(views)
    }


    /**
     * To next page in list
     */
    private fun next() {
        sequentialNavigator.navigateForward()
    }


    /**
     * Navigate backwards
     *
     * Hand navigation control back to the NavController when there
     * are no more custom views
     */
    private fun prev() {
        val success = sequentialNavigator.navigateBackwards()
        if (!success) {
            findNavController().popBackStack()
        }
    }


    /**
     * Intercept onBackPressed
     */
    private val onBackPressedCallback : OnBackPressedCallback = object : OnBackPressedCallback(true){
        override fun handleOnBackPressed() {
            prev()
        }
    }

}