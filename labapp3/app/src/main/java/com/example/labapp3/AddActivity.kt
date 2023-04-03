package com.example.labapp3

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val cancelButton: Button = findViewById(R.id.cancelButton)
        cancelButton.setOnClickListener {
            onCancelButtonClick()
        }

        val addButton: Button = findViewById(R.id.addButton)
        addButton.setOnClickListener {
            onAddButtonClick()
        }
    }

    fun onCancelButtonClick(){
        val resultIntent: Intent = Intent()
        setResult(RESULT_CANCELED, resultIntent)
        finish()
    }

    fun onAddButtonClick(){
        val editText: EditText = findViewById(R.id.editText)
        val editTextStr: String = editText.text.toString()
        val resultIntent: Intent = Intent().apply{
            putExtra("value", editTextStr)
        }
        setResult(RESULT_OK, resultIntent)
        finish()
    }
}