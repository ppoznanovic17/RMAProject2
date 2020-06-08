package com.example.rma2.presentation.view.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rma2.R
import kotlinx.android.synthetic.main.activity_login.*

class LogInActivity: AppCompatActivity(R.layout.activity_login) {

    companion object{
        const val PIN  = 1234
        const val STUDENT_USERNAME = "username"
        const val username_const = "ppoznanovic17"
        const val password_const = "aaa"

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initListeners()

    }

    private fun initListeners(){
        btnLogin.setOnClickListener {
            val editor = getSharedPreferences(packageName, Context.MODE_PRIVATE).edit()

            val username = usernameLoginEt.text.toString()
            val password = passwordLoginEt.text.toString()
            val pinString = pinLoginEt.text.toString()
            if(pinString.length != 4){
                Toast.makeText(this, "Pin mora bti duzi od 4 karaktera.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val pin = Integer.parseInt(pinString)

            if(pin != PIN){
                Toast.makeText(this, "Pogresan pin.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(username.isEmpty() || password.isEmpty())
            {
                Toast.makeText(this, "Ne sme biti praznih polja.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if(username_const != username || password != password_const)
            {
                Toast.makeText(this, "Losi kredencijali.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            editor.putBoolean(SplashActivity.LOGGED, true)
            editor.putString(STUDENT_USERNAME, username)

            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)


        }
    }



}