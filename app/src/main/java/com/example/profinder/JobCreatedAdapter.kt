package com.example.profinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

interface ItemClickListener {
    fun onItemClick(position: Int)
}

class JobCreatedAdapter (private val dataList: ArrayList<JobsDataClass>,
                         private val itemClickListener: ItemClickListener
) : RecyclerView.Adapter<JobCreatedAdapter.ViewHolderClass>() {

    class ViewHolderClass(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val rvLogo: ImageView = itemView.findViewById(R.id.homeCompanyLogo)
        val rvJobTitle: TextView = itemView.findViewById(R.id.homeJobTitle)
        val rvCompany: TextView = itemView.findViewById(R.id.homeCompanyName)
        val rvSalary: TextView = itemView.findViewById(R.id.homeSalary)
        val rvLocation: TextView = itemView.findViewById(R.id.homeJobLoc)
        val rvJobType: TextView = itemView.findViewById(R.id.homeJobType)
        val rvDatePosted: TextView = itemView.findViewById(R.id.homeDatePosted)
//        val rvDesc: TextView = itemView.findViewById(R.id.sheetJobDesc)
//        val rvResponsibilities: TextView = itemView.findViewById(R.id.sheetResponsibilites)
//        val rvQualifications: TextView = itemView.findViewById(R.id.sheetQualifications)
//        val rvBenefits: TextView = itemView.findViewById(R.id.sheetBenefits)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.jobcreated_card_layout, parent, false)
        return ViewHolderClass(itemView)
    }
    override fun getItemCount(): Int {
        return dataList.size
    }
    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentItem = dataList[position]
        /*holder.rvLogo.setImageResource(currentItem.jdCompanyLogo)*/
        holder.rvJobTitle.text = currentItem.jobTitle
        holder.rvCompany.text = currentItem.offeror
        holder.rvSalary.text = currentItem.jobSalary
        holder.rvLocation.text = currentItem.jobLoc
        holder.rvJobType.text = currentItem.jobType

        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(position)
        }
    }
}
