package com.example.healthcheckup.authenticationFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.healthcheckup.R
import com.example.healthcheckup.databinding.FragmentRegisterBinding
import com.example.healthcheckup.model.Users
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterFragment : Fragment() {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var dbRef: DatabaseReference
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.regBtn.setOnClickListener {
            userAuthorization()
        }
        binding.textView7.setOnClickListener {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }

    private fun userAuthorization() {
        firebaseAuth = FirebaseAuth.getInstance()

        val userName = binding.fullname.text.toString()
        val mailId = binding.mail.text.toString()
        val pswd = binding.pswd.text.toString()
        val repswd = binding.recheck.text.toString()

        if (userName.isNotEmpty() && mailId.isNotEmpty() && pswd.isNotEmpty() && repswd.isNotEmpty()) {
            if (pswd == repswd) {
                firebaseAuth.createUserWithEmailAndPassword(mailId, pswd).addOnCompleteListener {
                    if (it.isSuccessful) {
                        addUserToDatabase(userName,mailId,firebaseAuth.currentUser?.uid!!)
                        findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
                       // activity?.finish()
                    } else {
                        Log.e("error:", it.exception.toString())
                    }
                }
            } else {
                Toast.makeText(requireContext(), "Password is not matching", Toast.LENGTH_SHORT)
                    .show()
            }
        } else {
            Toast.makeText(requireContext(), "Fill the Fields properly", Toast.LENGTH_SHORT).show()
        }

    }
   private fun addUserToDatabase(userName:String, mailId:String, uid: String){
        dbRef = FirebaseDatabase.getInstance().getReference("Users")
        val user = Users(userName,mailId,uid)
        dbRef.child(userName).setValue(user).addOnSuccessListener {
            binding.fullname.text.toString()
            binding.mail.text.toString()
            binding.pswd.text.toString()
            binding.recheck.text.toString()

            Toast.makeText(requireContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(requireContext(), "Failed", Toast.LENGTH_SHORT).show()
        }
   }
}