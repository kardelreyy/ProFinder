package com.example.profinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast

class EditJob : AppCompatActivity() {

    private lateinit var databaseHelper : DBHelper
    private var jobId : Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_job)

        val editJobTitle: EditText = findViewById(R.id.updateJobName)
        val editOfferor: EditText = findViewById(R.id.updateCompany)
        val editSalary: EditText = findViewById(R.id.updateSalary)
        val editLoc: EditText = findViewById(R.id.updateLocation)
        val editStatus: Spinner = findViewById(R.id.spinnerUdStatus)
        val editJobType: Spinner = findViewById(R.id.spinnerUdJobType)
        val editDesc: EditText = findViewById(R.id.updateJobDescription)
        val editRespon: EditText = findViewById(R.id.updateResponsibilities)
        val editQuali: EditText = findViewById(R.id.updateQualifications)
        val editBenefits: EditText = findViewById(R.id.updateBenefits)

        val back = findViewById<Button>(R.id.backBtn)
        val saveChanges = findViewById<Button>(R.id.updateJobBtn)

        databaseHelper = DBHelper(this)
        jobId = intent.getIntExtra("jobId", -1)

        if (jobId != -1) {
            val job = databaseHelper.getJobById(jobId)

            if (job != null) {
                val statusArray = resources.getStringArray(R.array.postedjob_status) // Replace with your array resource
                val statPos = statusArray.indexOf(job.jobStatus)
                editStatus.setSelection(statPos)

                val jobTypeArray = resources.getStringArray(R.array.postedjob_types) // Replace with your array resource
                val typePos = jobTypeArray.indexOf(job.jobType)
                editJobType.setSelection(typePos)

                editJobTitle.setText(job.jobTitle)
                editOfferor.setText(job.offeror)
                editSalary.setText(job.jobSalary)
                editLoc.setText(job.jobLoc)
                editDesc.setText(job.jobDesc)
                editRespon.setText(job.jobResponsibility)
                editQuali.setText(job.jobQualifications)
                editBenefits.setText(job.jobBenefits)

                saveChanges.setOnClickListener {
                    val updatedDbTitle = editJobTitle.text.toString()
                    val updatedDbOfferor = editOfferor.text.toString()
                    val updatedDbSalary = editSalary.text.toString()
                    val updatedDbLoc = editLoc.text.toString()
                    val updatedDbStatus = editStatus.selectedItem.toString()
                    val updatedDbType = editJobType.selectedItem.toString()
                    val updatedDbDesc = editDesc.text.toString()
                    val updatedDbRespon = editRespon.text.toString()
                    val updatedDbQuali = editQuali.text.toString()
                    val updatedDbBenefits = editBenefits.text.toString()

                    val updatedJob = JobsDataClass(
                        jobId,
                        job.userIdFK,
                        updatedDbTitle,
                        updatedDbOfferor,
                        updatedDbSalary,
                        updatedDbLoc,
                        updatedDbStatus,
                        updatedDbType,
                        updatedDbDesc,
                        updatedDbRespon,
                        updatedDbQuali,
                        updatedDbBenefits
                    )
                    databaseHelper.updateJob(updatedJob)
                    Toast.makeText(this, "Job Updated", Toast.LENGTH_SHORT).show()
                    finish()
                    val i = Intent(this, MainActivity::class.java)
                    startActivity(i)
                }

                back.setOnClickListener {
                    // Handle button click, for example, show a toast or navigate to a new activity
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
            } else {
                Toast.makeText(this, "Job Update Unsuccessful", Toast.LENGTH_SHORT).show()
            }
        }
    }
}