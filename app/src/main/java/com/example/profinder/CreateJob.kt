package com.example.profinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast

class CreateJob : AppCompatActivity() {
    private lateinit var dbHelper: DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_job)

        dbHelper = DBHelper(this)

        val accountIdFk = 1
        val title = findViewById<EditText>(R.id.editJobName)
        val offeror = findViewById<EditText>(R.id.editCompany)
        val salary = findViewById<EditText>(R.id.editSalary)
        val location = findViewById<EditText>(R.id.editLocation)
        val status = findViewById<Spinner>(R.id.spinnerStatus)
        val type = findViewById<Spinner>(R.id.spinnerJobType)
        val desc = findViewById<EditText>(R.id.editJobDescription)
        val responsibility = findViewById<EditText>(R.id.editResponsibilities)
        val qualifications = findViewById<EditText>(R.id.editQualifications)
        val benefits = findViewById<EditText>(R.id.editBenefits)

        val back = findViewById<Button>(R.id.backBtn)
        val save = findViewById<Button>(R.id.saveJobBtn)

        save.setOnClickListener {
            val dbTitle = title.text.toString()
            val dbOfferor = offeror.text.toString()
            val dbSalary = salary.text.toString()
            val dbLoc = location.text.toString()
            val dbStatus = status.selectedItem.toString()
            val dbType = type.selectedItem.toString()
            val dbDesc = desc.text.toString()
            val dbRespon = responsibility.text.toString()
            val dbQuali = qualifications.text.toString()
            val dbBenefits = benefits.text.toString()

            // validation for empty fields, di magproceed
            if (dbTitle.isEmpty() ||  dbOfferor.isEmpty() || dbSalary.isEmpty() || dbLoc.isEmpty() || dbStatus.isEmpty()
                || dbType.isEmpty() || dbDesc.isEmpty() || dbRespon.isEmpty() || dbQuali.isEmpty() || dbBenefits.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_LONG).show()
            } else {
                // if fields are ano, magadd na sha
                val job = JobsDataClass(0, 0, dbTitle, dbOfferor, dbSalary, dbLoc, dbStatus, dbType, dbDesc, dbRespon, dbQuali, dbBenefits)
                val insertJob = dbHelper.insertJob(job)

                finish()
                if (insertJob != 1L) {
                    Toast.makeText(this, "Job Created Successfully", Toast.LENGTH_SHORT).show()
                } else {
                    // Validation if register process failed
                    Toast.makeText(this, "Creation Failed, Please try again", Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }

        back.setOnClickListener {
            // Handle button click, for example, show a toast or navigate to a new activity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}