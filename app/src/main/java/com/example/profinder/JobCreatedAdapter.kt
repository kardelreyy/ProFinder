package com.example.profinder

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class JobCreatedAdapter (private var jobList: List<JobsDataClass>) : RecyclerView.Adapter<JobCreatedAdapter.ViewHolderClass>() {

    private lateinit var myListener: onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: Int)
        fun onEditClick(position: Int)
    }

    fun setOnItemClickListener(listener: Any){
        myListener = listener as onItemClickListener
    }

    class ViewHolderClass(itemView: View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView) {
        val rvJobTitle: TextView = itemView.findViewById(R.id.homeJobTitle)
        val rvCompany: TextView = itemView.findViewById(R.id.homeCompanyName)
        val rvSalary: TextView = itemView.findViewById(R.id.homeSalary)
        val rvLocation: TextView = itemView.findViewById(R.id.homeJobLoc)
        val rvJobType: TextView = itemView.findViewById(R.id.homeJobType)
//        val rvDesc: TextView = itemView.findViewById(R.id.sheetJobDesc)
//        val rvResponsibilities: TextView = itemView.findViewById(R.id.sheetResponsibilites)
//        val rvQualifications: TextView = itemView.findViewById(R.id.sheetQualifications)
//        val rvBenefits: TextView = itemView.findViewById(R.id.sheetBenefits)

         val editJob: Button = itemView.findViewById(R.id.editJob)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }

            editJob.setOnClickListener {
                listener.onEditClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.jobcreated_card_layout, parent, false)
        return ViewHolderClass(itemView, myListener)
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentJob = jobList[position]
        holder.rvJobTitle.text = currentJob.jobTitle
        holder.rvCompany.text = currentJob.offeror
        holder.rvSalary.text = currentJob.jobSalary
        holder.rvLocation.text = currentJob.jobLoc
        holder.rvJobType.text = currentJob.jobType
//        holder.rvDesc.text = currentItem.jobDesc
//        holder.rvResponsibilities.text = currentItem.jobResponsibility
//        holder.rvQualifications.text = currentItem.jobQualifications
//        holder.rvBenefits.text = currentItem.jobBenefits

        /*holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(position)
        }*/

        /*holder.editJob.setOnClickListener {
            val toUpdateJob = Intent(holder.itemView.context, EditJob::class.java)
            toUpdateJob.putExtra("jobId", currentJob.jobId)
            holder.itemView.context.startActivity(toUpdateJob)
        }*/
    }

    fun refreshData(addedJobs: List<JobsDataClass>) {
        jobList = addedJobs
        notifyDataSetChanged()
    }

    fun getJobs(): List<JobsDataClass> {
        return jobList
    }

    override fun getItemCount(): Int { return jobList.size }
}
