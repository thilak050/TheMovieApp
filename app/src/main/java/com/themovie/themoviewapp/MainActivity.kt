package com.themovie.themoviewapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()

        //intent fron splash screen to home page with delay

        Handler().postDelayed({
            startActivity(Intent(this@MainActivity, HomePage::class.java))
            finish()
        }, 3000)
    }
}
