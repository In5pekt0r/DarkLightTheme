package com.ahriman.sendinfo

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class InfoActivity: AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }
        setContentView(R.layout.info_activity)
        var nameText: EditText = findViewById(R.id.editTextTextPersonNameSend)
        var ageText: EditText = findViewById(R.id.editTextNumberSend)
        var addressText: EditText = findViewById(R.id.editTextTextPostalAddressSend)
        var emailText: EditText = findViewById(R.id.editTextTextEmailAddressSend)
        nameText.setText(intent.getStringExtra("Name"))
        ageText.setText(intent.getStringExtra("Age"))
        addressText.setText(intent.getStringExtra("Address"))
        emailText.setText(intent.getStringExtra("Email"))
        nameText.isEnabled = false
        ageText.isEnabled = false
        addressText.isEnabled = false
        emailText.isEnabled = false


        var button: Button = findViewById<Button>(R.id.SendButton)
        button.setOnClickListener{
            val textInfo: String = "Name: " + intent.getStringExtra("Name") + ";" +
                    "Age: " + intent.getStringExtra("Age")+"; "+
                    "Address: " + intent.getStringExtra("Address")+"; "+
                    "Email: " + intent.getStringExtra("Email")+";"

            val sendIntent: Intent = Intent().apply{
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, textInfo)
                type = "text/plain"
            }
            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }


    }
}