package com.example.profinder

data class JobsDataClass(
    val jobId : Int,
    val userIdFK : Int,
    val jobTitle : String,
    val offeror : String,
    val jobSalary : String,
    val jobLoc : String,
    val jobType : String,
    var jobDesc : String? = null,
    var jobResponsibility : String? = null,
    var jobQualifications : String? = null,
    var jobBenefits : String? = null
)
