package com.example.healthcheckup.authenticationFragment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.healthcheckup.R
import com.example.healthcheckup.databinding.FragmentLoginBinding
import com.google.firebase.auth.FirebaseAuth


class LoginFragment : Fragment() {
   private lateinit var binding:FragmentLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadData()

        binding.loginBtn.setOnClickListener {
            userVerification()
        }
        binding.textView7.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    private fun userVerification(){
        firebaseAuth = FirebaseAuth.getInstance()

        val mailId = binding.mail.text.toString()
        val pswd = binding.pswd.text.toString()
        val checkbox = binding.checkBox.isChecked

        if(mailId.isNotEmpty() && pswd.isNotEmpty()){
            firebaseAuth.signInWithEmailAndPassword(mailId,pswd).addOnCompleteListener {
                if(it.isSuccessful){
                    if (checkbox == true){
                        saveData(mailId,pswd)
                    }
                    findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                }else{
                    Toast.makeText(requireContext(), it.exception.toString(), Toast.LENGTH_SHORT).show()
                    Log.e("error:",it.exception.toString())
                }
            }
        }else{
            Toast.makeText(requireContext(), "No Empty Fields are allowed", Toast.LENGTH_SHORT).show()
        }

//        if(FirebaseAuth.getInstance().currentUser != null){
//            findNavController().navigate(R.id.action_loginFragment_to_diabetesFragment)
//        }else{
//            Toast.makeText(requireContext(), "please login first", Toast.LENGTH_SHORT).show()
//        }
    }

    private fun saveData(mailId:String,pswd:String){
        val sharedPreferences = this.requireActivity().getSharedPreferences("pref", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.apply(){
            putString("StringKey1",mailId)
            putString("StringKey2",pswd)
            putBoolean("BooleanKey",binding.checkBox.isChecked)
        }.apply()
        Toast.makeText(requireContext(), "Data Saved", Toast.LENGTH_SHORT).show()
    }

    private fun loadData(){
        val sharedPreferences = this.requireActivity().getSharedPreferences("pref", Context.MODE_PRIVATE)
        val savedString1 = sharedPreferences.getString("StringKey1",null)
        val savedString2 = sharedPreferences.getString("StringKey2",null)
        val savedBool = sharedPreferences.getBoolean("BooleanKey",false)
        binding.mail.text = savedString1 as Editable?
        binding.pswd.text = savedString2 as Editable?
        binding.checkBox.isChecked = savedBool

        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
    }

}