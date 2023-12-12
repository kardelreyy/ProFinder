package com.example.profinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.model.AdapterClass
import androidx.recyclerview.widget.RecyclerView

class AppliedJobAdapter(private val dataList: ArrayList<AppliedJobDataClass>): RecyclerView.Adapter<AppliedJobAdapter.ViewHolderClass>() {

    class ViewHolderClass(itemView: View): RecyclerView.ViewHolder(itemView) {
        val rvJobTitle: TextView = itemView.findViewById(R.id.applyJobTitle)
        val rvStatus: TextView = itemView.findViewById(R.id.applyStatus)
        val rvCompany:TextView = itemView.findViewById(R.id.applyCompanyName)
        val rvSalary:TextView = itemView.findViewById(R.id.applySalary)
        val rvLocation:TextView = itemView.findViewById(R.id.applyJobLoc)
        val rvJobType:TextView = itemView.findViewById(R.id.applyJobType)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.appliedjob_tracker_card_layout, parent, false)
        return ViewHolderClass(itemView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentItem = dataList[position]
        holder.rvJobTitle.text = currentItem.adJobTitle
        holder.rvStatus.text = currentItem.adStatus
        holder.rvCompany.text = currentItem.adCompanyName
        holder.rvSalary.text = currentItem.adSalary
        holder.rvLocation.text = currentItem.adJobLoc
        holder.rvJobType.text = currentItem.adJobType
    }

}
