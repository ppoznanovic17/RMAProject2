package com.example.rma2.presentation.view.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rma2.R

class SplashActivity: AppCompatActivity() {

    companion object {
        const val LOGGED = "log"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPref = getSharedPreferences(packageName, Context.MODE_PRIVATE)
        val logged = sharedPref.getBoolean(LOGGED, false)

        if(!logged){
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)

        }else{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        finish()

    }

}