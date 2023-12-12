package com.example.profinder

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment(){
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var dbHelper: DBHelper

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        dbHelper = DBHelper(requireContext())

        /*        val sharedPreferences =
            requireContext().getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
        val userId = sharedPreferences.getInt("USER_ID", 0)*/

        val jobList = dbHelper.viewAllJobs()

        // Set up RecyclerView
        val recyclerView = view.findViewById<RecyclerView>(R.id.myHomeRecyclerView)
        val adapter = JobCreatedAdapter(jobList) // Pass the listener to the adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        adapter.setOnItemClickListener(object : JobCreatedAdapter.onItemClickListener {
            override fun onItemClick(position: Int) {
                val selectedJob = adapter.getJobs()[position]
                // Open JobDetailActivity with details of the clicked job
                val intent = Intent(requireContext(), JobDetailActivity::class.java)
                intent.putExtra("jobId", selectedJob.jobId)
                intent.putExtra("jobTitle", selectedJob.jobTitle)
                intent.putExtra("jobCompany", selectedJob.offeror)
                intent.putExtra("jobSalary", selectedJob.jobSalary)
                intent.putExtra("jobLocation", selectedJob.jobLoc)
                intent.putExtra("jobType", selectedJob.jobType)
                intent.putExtra("jobDesc", selectedJob.jobDesc)
                intent.putExtra("jobBenefits", selectedJob.jobBenefits)
                intent.putExtra("jobQualy", selectedJob.jobQualifications)
                intent.putExtra("jobResp", selectedJob.jobResponsibility)
                startActivity(intent)

                /*val intent = Intent(requireContext(), JobDetailActivity::class.java)
                intent.putExtra("position", position)
                startActivity(intent)*/
            }

            override fun onEditClick(position: Int) {
                val selectedJob = adapter.getJobs()[position]
               val intent = Intent(requireContext(), EditJob::class.java)
                intent.putExtra("jobId", selectedJob.jobId)
                startActivity(intent)
            }
        })
        return view
    }


}

