package com.example.profinder

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class ApplyFragment : Fragment() {
    private lateinit var myCardView: CardView
    private lateinit var daRecyclerView: RecyclerView
    private lateinit var dataList: ArrayList<AppliedJobDataClass>
    private lateinit var logoList : Array<Int>
    private lateinit var jobTitleList : Array<String>
    private lateinit var statusList : Array<String>
    private lateinit var companyNameList : Array<String>
    private lateinit var salaryList : Array<String>
    private lateinit var jobLocationList : Array<String>
    private lateinit var jobTypeList : Array<String>
    private lateinit var lastUpdatedList : Array<String>
    private lateinit var databaseHelper: DBHelper


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val applyView = inflater.inflate(R.layout.fragment_apply, container, false)

        databaseHelper = DBHelper(requireContext())

        val sharedPreferences = requireContext().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getInt("USER_ID", 0)
        // Get all pills from the database
        val appliedList = databaseHelper.getAllApplyByUserId(userId)
//        if (appliedList.isEmpty()){
//            Toast.makeText(activity, "Walang laman", Toast.LENGTH_SHORT).show()
//        }
        // Set up RecyclerView
        val recyclerView = applyView.findViewById<RecyclerView>(R.id.myAppliedRecyclerView)
        val adapter = AppliedJobAdapter(appliedList) // Create your adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter



        return applyView
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        daRecyclerView = view.findViewById(R.id.myAppliedRecyclerView)
//
//        val itemLayout: View = layoutInflater.inflate(R.layout.appliedjob_tracker_card_layout, null)
//        myCardView = itemLayout.findViewById(R.id.appliedJobCard)
//
//        daRecyclerView.layoutManager = LinearLayoutManager(requireContext())
//        daRecyclerView.setHasFixedSize(true)
//
//
//    }

//    private fun getData(){
//        for(i in logoList.indices){
//            val dataApply = AppliedJobDataClass(
//                logoList[i],
//                jobTitleList[i],
//                statusList[i],
//                companyNameList[i],
//                salaryList[i],
//                jobLocationList[i],
//                jobTypeList[i],
//                lastUpdatedList[i]
//            )
//            dataList.add(dataApply)
//        }
//
//        daRecyclerView.adapter = AppliedJobAdapter(dataList)
//    }


}