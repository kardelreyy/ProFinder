package com.example.profinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class JobDetailActivity : AppCompatActivity() {
    //basta ito yung activity na lalabas kapag pinindot yung card sa homepage
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_job_details)

        val backToHome : Button = findViewById(R.id.btnBack)
        backToHome.setOnClickListener {
            // Handle button click, for example, show a toast or navigate to a new activity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val intent = intent
        val jobId = intent.getIntExtra("jobId", 0)
        val jobTitle = intent.getStringExtra("jobTitle")
        val jobCompany = intent.getStringExtra("jobCompany")
        val jobSalary = intent.getStringExtra("jobSalary")
        val jobLocation = intent.getStringExtra("jobLocation")
        val jobType = intent.getStringExtra("jobType")
        val jobDesc = intent.getStringExtra("jobDesc")
        val jobBenefits = intent.getStringExtra("jobBenefits")
        val jobQualy = intent.getStringExtra("jobQualy")
        val jobResp = intent.getStringExtra("jobResp")

        val sheetTitle = findViewById<TextView>(R.id.sheetJobTitle)
        sheetTitle.text = jobTitle

        val sheetCompanyName = findViewById<TextView>(R.id.sheetCompanyName)
        sheetCompanyName.text = jobCompany

        val sheetSalary = findViewById<TextView>(R.id.sheetSalary)
        sheetSalary.text = jobSalary

        val sheetLocation = findViewById<TextView>(R.id.sheetLocation)
        sheetLocation.text = jobLocation

        val sheetJbType = findViewById<TextView>(R.id.sheetJbType)
        sheetJbType.text = jobType

        val sheetJobDesc = findViewById<TextView>(R.id.sheetJobDesc)
        sheetJobDesc.text = jobDesc

        val sheetResponsibilites = findViewById<TextView>(R.id.sheetResponsibilites)
        sheetResponsibilites.text = jobResp

        val sheetQualifications = findViewById<TextView>(R.id.sheetQualifications)
        sheetQualifications.text = jobQualy

        val sheetBenefits = findViewById<TextView>(R.id.sheetBenefits)
        sheetBenefits.text = jobBenefits


    }
}