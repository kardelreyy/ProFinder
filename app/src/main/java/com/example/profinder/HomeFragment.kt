package com.example.profinder

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment(), ItemClickListener {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var myCardView: CardView
    private lateinit var daRecyclerView: RecyclerView
    private lateinit var dataList: ArrayList<JobCreatedDataClass>
    private lateinit var logoList : Array<Int>
    private lateinit var jobNameList : Array<String>
    private lateinit var companyNameList : Array<String>
    private lateinit var salaryList : Array<String>
    private lateinit var jobLocationList : Array<String>
    private lateinit var jobTypeList : Array<String>
    private lateinit var datePostedList : Array<String>

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
            "King of the Pirates",
            "Interior Designer",
            "Librarian",
            "Systems Analyst"
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

        datePostedList = arrayOf(
            "5d ago",
            "4d ago",
            "3d ago",
            "2h ago",
            "1m ago"
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val itemLayout: View = layoutInflater.inflate(R.layout.jobcreated_card_layout, null)
        myCardView = itemLayout.findViewById(R.id.jobCard)

        daRecyclerView = view.findViewById(R.id.myHomeRecyclerView)
        daRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        daRecyclerView.setHasFixedSize(true)

        dataList = arrayListOf<JobCreatedDataClass>()
        getData()

        val adapter = JobCreatedAdapter(dataList, this)
        daRecyclerView.adapter = adapter
    }

    private fun getData(){
        for(i in logoList.indices){
            val dataHome = JobCreatedDataClass(
                logoList[i],
                jobNameList[i],
                companyNameList[i],
                salaryList[i],
                jobLocationList[i],
                jobTypeList[i],
                datePostedList[i]
            )
            dataList.add(dataHome)
        }
    }

    override fun onItemClick(position: Int) {
        // Handle item click, for example, navigate to a new activity
        val intent = Intent(requireContext(), JobDetailActivity::class.java)
        intent.putExtra("position", position)
        startActivity(intent)
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

}