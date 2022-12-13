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
                R.id.LightButton -> setTheme(AppCompatDelegate.MODE_NIGHT_NO, 0)
                R.id.DarkButton -> setTheme(AppCompatDelegate.MODE_NIGHT_YES, 1)
                R.id.AutoButton -> setTheme(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY, 2)
            }
        }
    }

    private fun setTheme(theme : Int, prefs: Int){
        AppCompatDelegate.setDefaultNightMode(theme)
        saveTheme(prefs)
    }
    private fun initTheme(){
        val lightButton: RadioButton = findViewById(R.id.LightButton)
        val darkButton: RadioButton = findViewById(R.id.DarkButton)
        val autoButton: RadioButton = findViewById(R.id.AutoButton)
        when(getSavedTheme()){
            0 -> lightButton.isChecked = true
            1 -> darkButton.isChecked = true
            2-> autoButton.isChecked = true
            -1 -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)


        }
    }
    private fun saveTheme(theme : Int) = sharedPrefs.edit().putInt("prefs.theme", theme).apply()

    private fun getSavedTheme() = sharedPrefs.getInt("prefs.theme", -1)
}

