package edu.uw.ischool.mmk49.quizdroid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Settings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_settings)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        var downloading = intent.getBooleanExtra("DOWNLOADING", false)
        val downloadBtn = findViewById<Button>(R.id.settingsButton)
        downloadBtn.setOnClickListener() {
            if(downloading) {
                //no problem, just change settings
            } else {
                //wait till download is done before messing with settings
            }
            //either way go back to main
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}