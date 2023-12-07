package com.example.profinder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class JobDetailActivity : AppCompatActivity() {
    //basta ito yung activity na lalabas kapag pinindot yung card sa homepage
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_job_details)

        val sampleButton: Button = findViewById(R.id.btnBack)

        // Set an OnClickListener for the button
        sampleButton.setOnClickListener {
            // Handle button click, for example, show a toast or navigate to a new activity
            val intent = Intent(this, HomeFragment::class.java)
            startActivity(intent)
        }
    }
}