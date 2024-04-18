package com.example.healthcheckup.diabetesCalculations

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.EditText
import com.example.healthcheckup.R
import com.example.healthcheckup.databinding.FragmentDiabetesRemedyBinding

class DiabetesRemedyFragment : Fragment() {
    private val diabetesStatus = arrayOf("Low","High")
    private lateinit var binding:FragmentDiabetesRemedyBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDiabetesRemedyBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDropdown()
    }
    private fun setDropdown(){
        val adapter = ArrayAdapter(requireContext(),android.R.layout.simple_list_item_1,diabetesStatus,)
        (binding.statusDropdown.editText as AutoCompleteTextView).apply {
            setOnDismissListener {
                binding.statusDropdown.clearFocus()
            }
        }?.setAdapter(adapter)
        binding.statusText.setOnItemClickListener { adapterView, view, i, l ->
            if(diabetesStatus[i] == "Low"){
                binding.view4.visibility = View.VISIBLE
                binding.lowRemedy.visibility = View.VISIBLE
                binding.highRemedy.visibility = View.GONE
            }else{
                binding.view4.visibility = View.VISIBLE
                binding.highRemedy.visibility = View.VISIBLE
                binding.lowRemedy.visibility = View.GONE
            }
        }
    }
}