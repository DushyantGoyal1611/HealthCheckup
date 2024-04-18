package com.example.healthcheckup

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.healthcheckup.R
import com.example.healthcheckup.databinding.FragmentStartBinding

class StartFragment : Fragment() {
    private lateinit var binding:FragmentStartBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.startedBtn.setOnClickListener {
            findNavController().navigate(R.id.action_startFragment_to_loginFragment)
        }
    }
}