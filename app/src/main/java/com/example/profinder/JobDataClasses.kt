package com.example.profinder

import android.widget.TextView
enum class DataType {
    HOME,
    EMPLOY
}

open class BaseData(
    val type: DataType
)

//for Home RecyclerView
data class DataHome(
    val hdCompanyLogo : Int,
    val hdJobTitle : String,
    val hdCompanyName : String,
    val hdSalary : String,
    val hdJobLoc : String,
    val hdJobType : String,
    val hdDatePosted : String
) : BaseData(DataType.HOME)

//for Employ RecyclerView
data class DataEmploy(
    var edJobTitle : String,
    var edStatus : String,
    var edBranch : String,
    var edDatePosted : String,
    var edApplicants : String,
    var edRejected : String,
    var edAccepted : String
) : BaseData(DataType.EMPLOY)

//for the page that appears after a card from Home is clicked
data class DataJob(
    var jdJobName : String,
    var jdCompanyName : String,
    var jdIndustry : String,
    var jdSalary : String,
    var jdLoc : String,
    var jdJobType : String,
    var jdJobDesc : String,
    var jdResponsibility : String,
    var jdQualifications : String,
    var jdBenefits : String
)

