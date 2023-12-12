package com.example.profinder

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity(){
    private lateinit var databaseHelper: DBHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val alrdyHavAnAcc =findViewById<TextView>(R.id.alrdyHavAnAcc)




        alrdyHavAnAcc.setOnClickListener(){
            goLogin()
        }



    }

     fun saveAddAccount(view: View) {
         //editText fields
         val emailTF = findViewById<EditText>(R.id.enterEmailLogin)
         val usernameTF = findViewById<EditText>(R.id.enterUName)
         val passwordTF = findViewById<EditText>(R.id.enterPasswordLogin)
         val FnameTF = findViewById<EditText>(R.id.enterFName)
         val LnameTF = findViewById<EditText>(R.id.enterLName)

         //get value of edit text
         val email = emailTF.text.toString()
         val username = usernameTF.text.toString()
         val password = passwordTF.text.toString()
         val Fname = FnameTF.text.toString()
         val Lname = LnameTF.text.toString()

         val errMsg = findViewById<TextView>(R.id.errorMsgTV)

         if (email.isNotEmpty() && username.isNotEmpty() && password.isNotEmpty() ) {
             if (isEmailValid(email)){
                 if (isValidPassword(password)){
                     val databaseHelper: DBHelper = DBHelper(this)
                     if (email.trim() != "" &&
                         username.trim() != "" &&
                         password.trim() != "" &&
                         Fname.trim() != "" &&
                         Lname.trim() != ""
                     ) {
                         val existingID: Int = -1
                         val status = databaseHelper.insertUser(
                             UserModelClass(
                                 existingID,
                                 email,
                                 username,
                                 password,
                                 Fname,
                                 Lname
                             )
                         )

                         if (status > -1) {
                             Toast.makeText(this, "Sign up successful", Toast.LENGTH_LONG).show()
                             emailTF.text.clear()
                             usernameTF.text.clear()
                             passwordTF.text.clear()
                             FnameTF.text.clear()
                             LnameTF.text.clear()

                             val i = Intent(this, LoginActivity::class.java)
                             startActivity(i)

                         } else {
                             Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
                         }
                     }
                 }else{
                     errMsg.text = "Invalid Password. (Requires 1 uppercase & lowercase alphabet, numeric value and symbol. Atleast 8 characters"
                 }
             }else{
                 errMsg.text = "Invalid Email. (name@domain.com)"
             }
         }else{
             errMsg.text = "Please make sure email, username, and password"
         }
     }

    //add account tas kuha from DBHelper ng addAccount function

    fun isValidPassword(password: String): Boolean {
        if (password.length < 8) {return false}
        if (password.filter { it.isDigit() }.firstOrNull() == null) {return false}
        if (password.filter { it.isLetter() }.filter { it.isUpperCase() }.firstOrNull() == null) {return false}
        if (password.filter { it.isLetter() }.filter { it.isLowerCase() }.firstOrNull() == null) {return false}
        if (password.filter { !it.isLetterOrDigit() }.firstOrNull() == null) {return false}

        return true
    }

    fun isEmailValid(email: String): Boolean {
        val emailPattern: Pattern = Pattern.compile(
    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
            "\\@" +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
            "(" +
            "\\." +
            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
            ")+"
        )
        return emailPattern.matcher(email).matches()
    }

    //navigate to Login Page
    private fun goToLogin(view: View) {
        goLogin()
    }
    private fun goLogin(){
        val yeetToLoginActivity = Intent(this, LoginActivity::class.java)
        startActivity(yeetToLoginActivity)
    }

}