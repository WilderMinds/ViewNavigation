package com.samdev.viewnavdemo.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.samdev.viewnavdemo.R
import com.samdev.viewnavdemo.databinding.ActivityMainBinding
import com.samdev.viewnavdemo.databinding.FragmentRootBinding

class RootFragment : Fragment() {

    private lateinit var binding: FragmentRootBinding

    companion object {
        fun newInstance() = RootFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRootBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnSequential.setOnClickListener {
            findNavController().navigate(R.id.action_rootFragment_to_sequentialViewsFragment)
        }

        binding.btnRandom.setOnClickListener {
            findNavController().navigate(R.id.action_rootFragment_to_randomViewsFragment)
        }
    }


}