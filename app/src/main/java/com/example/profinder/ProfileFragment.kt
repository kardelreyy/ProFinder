package com.example.profinder

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment(){
    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

    }

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

        val databaseHelper:DBHelper= DBHelper(requireContext())
        val userModelClass: List<UserModelClass> =databaseHelper.getUserData()

        if (userModelClass.isNotEmpty()){
            val userDetails = userModelClass[0]
            val userEmailTV = view.findViewById<TextView>(R.id.emailTV)
            val userNameTV = view.findViewById<TextView>(R.id.userNameTV)

            userEmailTV.text = "${userDetails.userEmail}"
//            userNameTV.text = "${userDetails.userName}"
        }else{
            Toast.makeText(requireContext(), "User doesn't exist", Toast.LENGTH_LONG).show()

        }
        return view
    }

}