package com.example.profinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class JobCreatedAdapter (private val jobList: List<JobsDataClass>) : RecyclerView.Adapter<JobCreatedAdapter.ViewHolderClass>() {

    private lateinit var myListener: onItemClickListener
    interface onItemClickListener{
        fun onItemClick(position: Int)
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
        val rvDesc: TextView = itemView.findViewById(R.id.sheetJobDesc)
        val rvResponsibilities: TextView = itemView.findViewById(R.id.sheetResponsibilites)
        val rvQualifications: TextView = itemView.findViewById(R.id.sheetQualifications)
        val rvBenefits: TextView = itemView.findViewById(R.id.sheetBenefits)

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.jobcreated_card_layout, parent, false)
        return ViewHolderClass(itemView, myListener)
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentItem = jobList[position]
        holder.rvJobTitle.text = currentItem.jobTitle
        holder.rvCompany.text = currentItem.offeror
        holder.rvSalary.text = currentItem.jobSalary
        holder.rvLocation.text = currentItem.jobLoc
        holder.rvJobType.text = currentItem.jobType
        holder.rvDesc.text = currentItem.jobDesc
        holder.rvResponsibilities.text = currentItem.jobResponsibility
        holder.rvQualifications.text = currentItem.jobQualifications
        holder.rvBenefits.text = currentItem.jobBenefits

        /*holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(position)
        }*/
    }

    fun getJobs(): List<JobsDataClass> {
        return jobList
    }

    override fun getItemCount(): Int { return jobList.size }
}
