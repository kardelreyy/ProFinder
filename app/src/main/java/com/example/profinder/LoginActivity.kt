package com.example.profinder

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

            loginUser(loginUsername)
        }
        }


    private fun loginUser (username: String){
        val UserExists = loginDBHelper.readUserByUsername(username)
        if (UserExists){
            Toast.makeText(this, "Login Successful", Toast.LENGTH_LONG).show()

            val i = Intent(this, MainActivity::class.java )
            startActivity(i)
            finish()
        }
    }
    fun goSignup (view: View){
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

}