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

class Employ : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var myCardView: CardView
    private lateinit var daRecyclerView: RecyclerView
    private lateinit var dataList: ArrayList<BaseData>
    private lateinit var jobNameList : Array<String>
    private lateinit var statusList : Array<String>
    private lateinit var datePostedList : Array<String>
    private lateinit var totalApplicantsList : Array<String>
    private lateinit var branchList : Array<String>
    private lateinit var rejectedNumList : Array<String>
    private lateinit var acceptedNumList : Array<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        jobNameList = arrayOf(
            "Barista",
            "King of the Pirates",
            "Interior Designer",
            "Librarian",
            "Systems Analyst"
        )

        statusList = arrayOf(
            "Hiring",
            "Hiring",
            "Closed",
            "Closed",
            "Hiring"
        )

        datePostedList = arrayOf(
            "5d ago",
            "4d ago",
            "3d ago",
            "2h ago",
            "1m ago"
        )

        totalApplicantsList = arrayOf(
            "10",
            "5",
            "23",
            "4",
            "2"
        )

        branchList = arrayOf(
            "North Fairview, Quezon City",
            "Marie Jois, Grand Line",
            "Tagum. Davao del Norte",
            "Sampaloc, Metro Manila",
            "Marikina, Metro Manila"
        )

        rejectedNumList = arrayOf(
            "0",
            "1",
            "15",
            "2",
            "0"
        )

        acceptedNumList = arrayOf(
            "0",
            "1",
            "8",
            "2",
            "0"
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_employ, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        daRecyclerView = view.findViewById(R.id.myEmployRecyclerView)

        val itemLayout: View = layoutInflater.inflate(R.layout.jobposted_tracker_card_layout, null)
        myCardView = itemLayout.findViewById(R.id.postedJobCard)

        daRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        daRecyclerView.setHasFixedSize(true)

        dataList = arrayListOf<BaseData>()
        getData()
    }

    private fun getData(){
        for(i in jobNameList.indices){
            val dataEmploy = DataEmploy(
                jobNameList[i],
                statusList[i],
                branchList[i],
                datePostedList[i],
                totalApplicantsList[i],
                rejectedNumList[i],
                acceptedNumList[i]
            )
            dataList.add(dataEmploy)
        }

        daRecyclerView.adapter = AdapterClass(dataList)
    }


    companion object {
        fun newInstance(param1: String, param2: String) =
            Employ().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}