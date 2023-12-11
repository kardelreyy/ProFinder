package com.example.profinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class JobPostedAdapter(private val dataList: ArrayList<JobPostedDataClass>): RecyclerView.Adapter<JobPostedAdapter.ViewHolderClass>() {

    class ViewHolderClass(itemView: View): RecyclerView.ViewHolder(itemView) {
        val rvJobTitle: TextView = itemView.findViewById(R.id.employJobTitle)
        val rvStatus: TextView = itemView.findViewById(R.id.employStatus)
        val rvBranch: TextView = itemView.findViewById(R.id.employBranch)
        val rvApplicants: TextView = itemView.findViewById(R.id.employTotalApplicants)
        val rvRejected: TextView = itemView.findViewById(R.id.employRejectNum)
        val rvAccepted: TextView = itemView.findViewById(R.id.employAcceptNum)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.jobposted_tracker_card_layout, parent, false)
        return ViewHolderClass(itemView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentItem = dataList[position]
        holder.rvJobTitle.text = currentItem.edJobTitle
        holder.rvStatus.text = currentItem.edStatus
        holder.rvBranch.text = currentItem.edBranch
        holder.rvApplicants.text = currentItem.edApplicants
        holder.rvRejected.text = currentItem.edRejected
        holder.rvAccepted.text = currentItem.edAccepted
    }
}