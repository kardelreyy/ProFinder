package com.example.profinder

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterClass(private val dataList: ArrayList<BaseData>): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private val VIEW_TYPE_HOME = 1;
    private val VIEW_TYPE_EMPLOY = 2;
    private val VIEW_TYPE_APPLIED = 3;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_TYPE_HOME -> {
                // Inflate layout for the 'homw' fragment
                val view = LayoutInflater.from(parent.context).inflate(R.layout.homepagejob_card_layout, parent, false)
                HomeViewHolder(view)
            }
            VIEW_TYPE_EMPLOY -> {
                // Inflate layout for the 'employ' fragment
                val view = LayoutInflater.from(parent.context).inflate(R.layout.jobposted_tracker_card_layout, parent, false)
                EmployViewHolder(view)
            }
            VIEW_TYPE_APPLIED -> {
                // Inflate layout for the 'applied' fragment
                val view = LayoutInflater.from(parent.context).inflate(R.layout.appliedjob_tracker_card_layout, parent, false)
                ApplyViewHolder(view)
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            VIEW_TYPE_HOME -> {
                val homeViewHolder = holder as HomeViewHolder
                homeViewHolder.bind(dataList[position] as DataHome) // Adjust to your data types
            }
            VIEW_TYPE_EMPLOY -> {
                val employViewHolder = holder as EmployViewHolder
                employViewHolder.bind(dataList[position] as DataEmploy) // Adjust to your data types
            }
            VIEW_TYPE_APPLIED -> {
                val applyViewHolder = holder as ApplyViewHolder
                applyViewHolder.bind(dataList[position] as DataApply) // Adjust to your data types
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun getItemViewType(position: Int): Int {
        return when (dataList[position].type) {
            DataType.HOME -> VIEW_TYPE_HOME
            DataType.EMPLOY -> VIEW_TYPE_EMPLOY
            DataType.APPLY -> VIEW_TYPE_APPLIED
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //giving the ids ng layout to variables para malaman ung location nila sa layout
        private val companyLogo : ImageView = itemView.findViewById(R.id.homeCompanyLogo)
        private val jobTitle : TextView = itemView.findViewById(R.id.homeJobTitle)
        private val companyName : TextView = itemView.findViewById(R.id.homeCompanyName)
        private val salary : TextView = itemView.findViewById(R.id.homeSalary)
        private val location : TextView = itemView.findViewById(R.id.homeJobLoc)
        private val jobType : TextView = itemView.findViewById(R.id.homeJobType)
        private val datePosted : TextView = itemView.findViewById(R.id.homeDatePosted)

        fun bind(data: DataHome) {
            // Bind data to views for the 'home' fragment
            // data is an instance of the DataClass
            //to bind this data to corresponding views in ur layout, use the properties of DataClass to set the attributes of other views.

            //format: HVH privateval.text = data.DataClassparam
            companyLogo.setImageResource(data.hdCompanyLogo)
            jobTitle.text = data.hdJobTitle
            companyName.text = data.hdCompanyName
            salary.text = data.hdSalary
            location.text = data.hdJobLoc
            jobType.text = data.hdJobType
            datePosted.text = data.hdDatePosted
        }
    }

    class EmployViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val jobTitle : TextView = itemView.findViewById(R.id.employJobTitle)
        private val status : TextView = itemView.findViewById(R.id.employStatus)
        private val branch : TextView = itemView.findViewById(R.id.employBranch)
        private val datePosted : TextView = itemView.findViewById(R.id.employDatePosted)
        private val totalApplicants : TextView = itemView.findViewById(R.id.employTotalApplicants)
        private val rejected : TextView = itemView.findViewById(R.id.employRejectNum)
        private val accepted : TextView = itemView.findViewById(R.id.employAcceptNum)

        fun bind(data: DataEmploy) {
            jobTitle.text = data.edJobTitle
            status.text = data.edStatus
            branch.text = data.edBranch
            datePosted.text = data.edDatePosted
            totalApplicants.text = data.edApplicants
            rejected.text = data.edRejected
            accepted.text = data.edAccepted
        }
    }

    class ApplyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val companyLogo : ImageView = itemView.findViewById(R.id.applyCompanyLogo)
        private val jobTitle : TextView = itemView.findViewById(R.id.applyJobTitle)
        private val status : TextView = itemView.findViewById(R.id.applyStatus)
        private val companyName : TextView = itemView.findViewById(R.id.applyCompanyName)
        private val salary : TextView = itemView.findViewById(R.id.applySalary)
        private val location : TextView = itemView.findViewById(R.id.applyJobLoc)
        private val jobType : TextView = itemView.findViewById(R.id.applyJobType)
        private val lastUpdated : TextView = itemView.findViewById(R.id.applyLastUpdated)

        fun bind(data: DataApply) {
            companyLogo.setImageResource(data.adCompanyLogo)
            jobTitle.text = data.adJobTitle
            status.text = data.adStatus
            companyName.text = data.adCompanyName
            salary.text = data.adSalary
            location.text = data.adJobLoc
            jobType.text = data.adJobType
            lastUpdated.text = data.adLastUpdated
        }
    }

}