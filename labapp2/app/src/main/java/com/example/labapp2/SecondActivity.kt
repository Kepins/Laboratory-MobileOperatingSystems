package com.example.labapp2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // textView
        val textView: TextView = findViewById(R.id.textView)
        textView.setText(intent.getIntExtra("value", 0).toString())


        //incButton
        val incButton : Button = findViewById(R.id.incButton)
        incButton.setOnClickListener { onIncButtonClick() }

        // decButton
        val decButton : Button = findViewById(R.id.decButton)
        decButton.setOnClickListener { onDecButtonClick() }

        // backButton
        val backButton : Button = findViewById(R.id.backButton)
        backButton.setOnClickListener { onBackButtonClick() }
    }

    private fun onIncButtonClick(){
        val textView: TextView = findViewById(R.id.textView)
        val intValue: Int = Integer.parseInt(textView.text.toString())
        textView.setText((intValue+1).toString())
    }

    private fun onDecButtonClick(){
        val textView: TextView = findViewById(R.id.textView)
        val intValue: Int = Integer.parseInt(textView.text.toString())
        textView.setText((intValue-1).toString())
    }

    private fun onBackButtonClick(){
        val textView: TextView = findViewById(R.id.textView)
        val intValue: Int = Integer.parseInt(textView.text.toString())
        val resultIntent: Intent = Intent().apply{
            putExtra("value", intValue)
        }
        setResult(RESULT_OK, resultIntent)
        finish()
    }
}