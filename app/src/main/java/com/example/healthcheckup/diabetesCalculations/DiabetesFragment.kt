package com.example.healthcheckup.diabetesCalculations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.navigation.fragment.findNavController
import com.example.healthcheckup.R
import com.example.healthcheckup.databinding.FragmentDiabetesBinding

class DiabetesFragment : Fragment() {
    private val diabetesStatus = arrayOf("Low", "High")
    private lateinit var binding: FragmentDiabetesBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDiabetesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDropdown()

        binding.diabetesBtn.setOnClickListener {
            checkDiabetes()
        }

        binding.remedyBtn.setOnClickListener {
            findNavController().navigate(R.id.action_diabetesFragment_to_diabetesRemedyFragment)
        }
    }

    private fun setDropdown() {
        val adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, diabetesStatus)
        (binding.statusDropdown.editText as? AutoCompleteTextView)?.apply {
            setOnDismissListener {
                binding.statusDropdown.clearFocus()
            }
        }?.setAdapter(adapter)
        binding.statusText.setOnItemClickListener { adapterView, view, i, l ->
            if (diabetesStatus[i] == "Low") {
                binding.lowText.visibility = View.VISIBLE
                binding.highText.visibility = View.GONE
            } else {
                binding.highText.visibility = View.VISIBLE
                binding.lowText.visibility = View.GONE
            }
        }
    }

    private fun checkDiabetes() {
        val sugarLevel = binding.editTextText.text.toString().toDouble()

        if (sugarLevel != null) {
            if (sugarLevel >= 140) {
                binding.textView2.text = "High"
            } else if (sugarLevel >= 80 && sugarLevel <= 139) {
                binding.textView2.text = "Normal"
            } else {
                binding.textView2.text = "Low"
            }
        }
    }
}