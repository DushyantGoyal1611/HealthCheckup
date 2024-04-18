package com.example.healthcheckup.fragments.bmicalculations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.healthcheckup.R
import com.example.healthcheckup.databinding.FragmentBMIResultBinding

class BMIResultFragment : Fragment() {
    private lateinit var binding:FragmentBMIResultBinding
    private val args:BMIResultFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBMIResultBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bmiResult = args.messageString

        val bmiStatus = bmiResult.toDouble()

        binding.bminumber.text = bmiResult

        if(bmiStatus <= 18.4){
            binding.status.text = "Underweight"
        }else if(bmiStatus>= 18.5 && bmiStatus<= 24.9){
            binding.status.text = "Healthy"
        }else if(bmiStatus>= 25.0 && bmiStatus<= 39.9){
            binding.status.text = "Overweight"
        }else{
            binding.status.text = "Obese"
        }
    }
}