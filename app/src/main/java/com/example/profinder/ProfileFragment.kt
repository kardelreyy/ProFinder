package com.example.profinder

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class ProfileFragment : Fragment(), ItemClickListener{
    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)

    }

    override fun onItemClick(position: Int) {
        TODO("Not yet implemented")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_employ, container, false)
        return view
    }

}