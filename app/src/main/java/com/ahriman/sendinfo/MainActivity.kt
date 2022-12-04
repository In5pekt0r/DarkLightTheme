package com.ahriman.sendinfo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var button: Button = findViewById<Button>(R.id.EditActivityButton)
        button.setOnClickListener{
            val intent =Intent(this, EditActivity::class.java)
            startActivity(intent)
        }
    }

}