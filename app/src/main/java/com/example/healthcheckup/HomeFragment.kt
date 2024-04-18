package com.example.healthcheckup

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.healthcheckup.adapter.CheckupAdapter
import com.example.healthcheckup.databinding.FragmentHomeBinding
import com.example.healthcheckup.model.Tests

class HomeFragment : Fragment() {
    private lateinit var itemList: ArrayList<Tests>
    private lateinit var checkupAdapter: CheckupAdapter

    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.logout.setOnClickListener {
            val sharedPreferences = this.requireActivity().getSharedPreferences("pref", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.apply(){
                putString("StringKey1"," ")
                putString("StringKey2"," ")
            }.apply()
        }
        setAdapter()
    }

    private fun setAdapter() {
        binding.recycler.setHasFixedSize(true)
        binding.recycler.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        itemList = arrayListOf()

        itemList.add(Tests(R.drawable.bmi, "BMI"))
        itemList.add(Tests(R.drawable.blood, "Diabetes"))

        checkupAdapter = CheckupAdapter(itemList, requireContext()) { itemPosition ->
            println(itemPosition)
            when (itemPosition) {
                0 -> findNavController().navigate(R.id.action_homeFragment_to_BMlFragment)
                1 -> findNavController().navigate(R.id.action_homeFragment_to_diabetesFragment)
                else -> println("Error")
            }
        }
        binding.recycler.adapter = checkupAdapter
    }
}