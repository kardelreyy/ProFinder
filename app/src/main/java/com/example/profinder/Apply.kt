package com.example.profinder

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class Apply : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var myCardView: CardView
    private lateinit var daRecyclerView: RecyclerView
    private lateinit var dataList: ArrayList<BaseData>
    private lateinit var logoList : Array<Int>
    private lateinit var jobNameList : Array<String>
    private lateinit var statusList : Array<String>
    private lateinit var companyNameList : Array<String>
    private lateinit var salaryList : Array<String>
    private lateinit var jobLocationList : Array<String>
    private lateinit var jobTypeList : Array<String>
    private lateinit var lastUpdatedList : Array<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        logoList = arrayOf(
            R.drawable.untitled_design__3_,
            R.drawable.untitled_design__3_,
            R.drawable.untitled_design__3_,
            R.drawable.untitled_design__3_,
            R.drawable.untitled_design__3_)

        jobNameList = arrayOf(
            "Barista",
            "Pirate King",
            "Interior Designer",
            "Librarian",
            "Systems Analyst"
        )

        statusList = arrayOf(
            "Waiting",
            "Hired",
            "Waiting",
            "Declined",
            "Declined"
        )

        companyNameList = arrayOf(
            "Starbucks",
            "One Piece",
            "VerdantPoint",
            "Manila Library",
            "Octane"
        )

        jobLocationList = arrayOf(
            "North Fairview, Quezon City",
            "Marie Jois, Grand Line",
            "Tagum. Davao del Norte",
            "Sampaloc, Metro Manila",
            "Marikina, Metro Manila"
        )

        salaryList = arrayOf(
            "P18,000",
            "P500,000,000",
            "P19,000",
            "P20,000",
            "P21,000"
        )

        jobTypeList = arrayOf(
            "Part-time",
            "One-time",
            "Full-time",
            "One-time",
            "Full-time"
        )

        lastUpdatedList = arrayOf(
            "26/11/2023",
            "27/11/2023",
            "28/11/2023",
            "29/11/2023",
            "30/11/2023"
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_apply, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        daRecyclerView = view.findViewById(R.id.myAppliedRecyclerView)

        val itemLayout: View = layoutInflater.inflate(R.layout.appliedjob_tracker_card_layout, null)
        myCardView = itemLayout.findViewById(R.id.appliedJobCard)

        daRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        daRecyclerView.setHasFixedSize(true)

        dataList = arrayListOf<BaseData>()
        getData()
    }

    private fun getData(){
        for(i in logoList.indices){
            val dataApply = DataApply(
                logoList[i],
                jobNameList[i],
                statusList[i],
                companyNameList[i],
                salaryList[i],
                jobLocationList[i],
                jobTypeList[i],
                lastUpdatedList[i]
            )
            dataList.add(dataApply)
        }

        daRecyclerView.adapter = AdapterClass(dataList)
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            Apply().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}