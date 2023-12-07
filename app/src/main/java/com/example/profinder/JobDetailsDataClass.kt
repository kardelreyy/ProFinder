package com.example.profinder

data class JobDetailsDataClass(
    val hdCompanyLogo : Int,
    val hdJobTitle : String,
    val hdCompanyName : String,
    val hdSalary : String,
    val hdJobLoc : String,
    val hdJobType : String,
    var jdJobDesc : String,
    var jdResponsibility : String,
    var jdQualifications : String,
    var jdBenefits : String
)
