package com.example.healthcheckup.fragments.bmicalculations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.healthcheckup.databinding.FragmentBMlBinding

class BMlFragment : Fragment() {

    private lateinit var binding: FragmentBMlBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentBMlBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.CalculateBtn.setOnClickListener {
            setHeightandWeight()
        }
    }

    private fun setHeightandWeight() {
        val height = binding.height.text.toString().toDoubleOrNull()
        val weight = binding.weight.text.toString().toDoubleOrNull()

        if (height != null && weight != null) {
            val finalBMIResult = String.format("%.2f", calculateBMI(height, weight))
            findNavController().navigate(BMlFragmentDirections.actionBMlFragmentToBMIResultFragment(finalBMIResult))
        } else {
            Toast.makeText(requireContext(), "Please set Height and Weight", Toast.LENGTH_SHORT)
                .show()
        }
    }

    private fun calculateBMI(height: Double, weight: Double): Double {
        return weight / (height * height)
    }

}