package com.samdev.viewnavdemo.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.navigation.fragment.findNavController
import com.samdev.viewnavdemo.databinding.FragmentRandomViewsBinding
import com.samdev.viewnavdemo.navigator.CustomNavigator

class RandomViewsFragment : Fragment() {

    private lateinit var binding: FragmentRandomViewsBinding
    private val customNavigator = CustomNavigator()


    companion object {
        fun newInstance() = RandomViewsFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedCallback)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRandomViewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListeners()
    }


    private fun initClickListeners() {

        // back pressed
        binding.btnBack.setOnClickListener {
            back()
        }

        // blue is just an arbitrary identifier
        setupBlueNav()

        // red is just an arbitrary identifier
        setupRedNav()
    }


    private fun setupBlueNav() {
        // route to blue
        binding.btnRouteToBlue.setOnClickListener {
            customNavigator.navigateForward(
                binding.viewParent,
                binding.viewBlueMid
            )
        }

        // final stage on blue
        binding.btnRouteToBlueFinal.setOnClickListener {
            customNavigator.navigateForward(
                binding.viewBlueMid,
                binding.viewBlueFinal
            )
        }
    }


    private fun setupRedNav() {
        binding.btnRouteToRed.setOnClickListener {
            customNavigator.navigateForward(
                binding.viewParent,
                binding.viewRedMid
            )
        }

        binding.btnRouteToRedFinal.setOnClickListener {
            customNavigator.navigateForward(
                binding.viewRedMid,
                binding.viewRedFinal
            )
        }
    }


    /**
     * Navigate backwards
     *
     * Hand navigation control back to the NavController when there
     * are no more custom views
     */
    private fun back() {
        val success = customNavigator.back();
        if (!success){
            findNavController().popBackStack()
        }
    }


    /**
     * Intercept onBackPressed
     */
    private val onBackPressedCallback : OnBackPressedCallback = object : OnBackPressedCallback(true){
        override fun handleOnBackPressed() {
            back()
        }
    }

}