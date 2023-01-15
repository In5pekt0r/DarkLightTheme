package com.ahriman.sendinfo

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {

    private val sharedPrefs by lazy{ getSharedPreferences("theme_prefs", Context.MODE_PRIVATE)}
    private val prefDoc:String = "theme_prefs"

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initThemeListener()
        initTheme()



        var button: Button = findViewById<Button>(R.id.EditActivityButton)
        button.setOnClickListener{
            val intent =Intent(this, EditActivity::class.java)
            startActivity(intent)
        }
    }
    private fun initThemeListener(){
        var themeGroup: RadioGroup = findViewById(R.id.themeGroup)
        themeGroup.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId){
                R.id.LightButton -> setCustomTheme(AppCompatDelegate.MODE_NIGHT_NO)
                R.id.DarkButton -> setCustomTheme(AppCompatDelegate.MODE_NIGHT_YES)
                R.id.AutoButton -> setCustomTheme(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }
        }
    }

    fun setCustomTheme(theme : Int){
        AppCompatDelegate.setDefaultNightMode(theme)
        saveTheme(theme)
    }
    private fun initTheme(){
        val lightButton: RadioButton = findViewById(R.id.LightButton)
        val darkButton: RadioButton = findViewById(R.id.DarkButton)
        val autoButton: RadioButton = findViewById(R.id.AutoButton)
        when(getSavedTheme()){
            AppCompatDelegate.MODE_NIGHT_NO -> lightButton.isChecked = true
            AppCompatDelegate.MODE_NIGHT_YES -> darkButton.isChecked = true
            AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM-> autoButton.isChecked = true

        }
    }
    private fun saveTheme(theme : Int) = sharedPrefs.edit().putInt(prefDoc, theme).apply()

    private fun getSavedTheme() = sharedPrefs.getInt(prefDoc, AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
}

