package com.example.labapp4

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // startServiceButton
        val startServiceButton: Button = findViewById(R.id.startServiceButton)
        startServiceButton.setOnClickListener{
            onStartServiceButtonClick()
        }

        // stopServiceButton
        val stopServiceButton: Button = findViewById(R.id.stopServiceButton)
        stopServiceButton.setOnClickListener{
            onStopServiceButtonClick()
        }

        // useServiceButton
        val useServiceButton: Button = findViewById(R.id.useServiceButton)
        useServiceButton.setOnClickListener{
            onUseServiceButtonClick()
        }

    }

    fun onStartServiceButtonClick(){
        val intentService = Intent(this, UpperCaseService::class.java)
        startService(intentService)
        val textView: TextView = findViewById(R.id.textView)
        textView.text = "Service active"
    }

    fun onStopServiceButtonClick(){
        val intentService = Intent()
        intentService.setClassName("com.example.labapp4", "com.example.labapp4.UpperCaseService")
        stopService(intentService)
        val textView: TextView = findViewById(R.id.textView)
        textView.text = "Service inactive"

    }

    fun onUseServiceButtonClick(){
        val useServiceActivityIntent: Intent = Intent(this, UseServiceActivity::class.java)
        startActivity(useServiceActivityIntent)
    }

}