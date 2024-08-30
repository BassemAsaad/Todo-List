package com.example.session9_todo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.session9_todo.home.HomeActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splach)
        Handler(Looper.getMainLooper())
            .postDelayed({
            startHomeScreen()},3000)
    }


    fun startHomeScreen(){
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}