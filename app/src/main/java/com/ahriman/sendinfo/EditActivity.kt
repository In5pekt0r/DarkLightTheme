package com.ahriman.sendinfo

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener

class EditActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }
        setContentView(R.layout.edit_activity)
        var button: Button = findViewById<Button>(R.id.SaveButton)

        var nameFlag: Boolean = false
        var ageFlag: Boolean = false
        var addressFlag: Boolean = false
        var emailFlag: Boolean = false
        var emptiness: Boolean = false
        var nameText: EditText = findViewById(R.id.editTextTextPersonName2)
        var ageText: EditText = findViewById(R.id.editTextNumber)
        var addressText: EditText = findViewById(R.id.editTextTextPostalAddress)
        var emailText: EditText = findViewById(R.id.editTextTextEmailAddress)


        nameText.addTextChangedListener (object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start:Int, count: Int, after:Int){}
            override fun onTextChanged(s: CharSequence?, start: Int, count: Int, after: Int){
                nameFlag = nameText.text.isNotEmpty() && nameText.text.toString().split(' ').all { it[0].isUpperCase() }
                emptiness = nameFlag && addressFlag && emailFlag && ageFlag
                button.isEnabled = emptiness
            }
            override fun afterTextChanged(s: Editable?){}
        })
        ageText.addTextChangedListener (object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start:Int, count: Int, after:Int){}
            override fun onTextChanged(s: CharSequence?, start: Int, count: Int, after: Int){
                ageFlag = ageText.text.isNotEmpty() && ageText.text.toString().length < 4
                emptiness = nameFlag && addressFlag && emailFlag && ageFlag
                button.isEnabled = emptiness
            }
            override fun afterTextChanged(s: Editable?){}
        })
        addressText.addTextChangedListener (object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start:Int, count: Int, after:Int){}
            override fun onTextChanged(s: CharSequence?, start: Int, count: Int, after: Int){
                addressFlag = addressText.text.isNotEmpty()
                emptiness = nameFlag && addressFlag && emailFlag && ageFlag
                button.isEnabled = emptiness
            }
            override fun afterTextChanged(s: Editable?){}
        })
        emailText.addTextChangedListener (object: TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start:Int, count: Int, after:Int){}
            override fun onTextChanged(s: CharSequence?, start: Int, count: Int, after: Int){
                emailFlag = emailText.text.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(emailText.text.toString()).matches()
                emptiness = nameFlag && addressFlag && emailFlag && ageFlag
                button.isEnabled = emptiness
            }
            override fun afterTextChanged(s: Editable?){}
        })

        button.setOnClickListener{
            val intent = Intent(this, InfoActivity::class.java)
            intent.putExtra("Name", nameText.text.toString())
            intent.putExtra("Age", ageText.text.toString())
            intent.putExtra("Address", addressText.text.toString())
            intent.putExtra("Email", emailText.text.toString())
            startActivity(intent)
        }
    }


}