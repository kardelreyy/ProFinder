package com.example.profinder

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.sax.StartElementListener
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.profinder.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var loginDBHelper: DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginDBHelper = DBHelper(this)

        binding.confirmLoginBtn.setOnClickListener {
            val loginUsername = binding.enterEmailLogin.text.toString()
            val loginPassword = binding.enterPasswordLogin.text.toString()

            loginUser(loginUsername,loginPassword)
        }
    }


    private fun loginUser (username: String,password:String){
        val LoginCorrect = loginDBHelper.readUserByUsername(UserModelClass(0, username, "", password, "",""))
        if (LoginCorrect != null){
            Toast.makeText(this, "Login Successful", Toast.LENGTH_LONG).show()

            // Save userId to SharedPreferences
            val sharedPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putInt("USER_ID", LoginCorrect.userId)
            editor.apply()

            val i = Intent(this, MainActivity::class.java )
            startActivity(i)
            finish()
        }
        else{
            Toast.makeText(this, "Incorrect Details", Toast.LENGTH_LONG).show()
        }
    }
    fun goSignup (view: View){
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

}