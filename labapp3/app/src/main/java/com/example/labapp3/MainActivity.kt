package com.example.labapp3

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    private lateinit var db: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = openOrCreateDatabase("DBlab3", MODE_PRIVATE, null)
        db.execSQL("CREATE TABLE IF NOT EXISTS lab3 (data VARCHAR);")

        // Add entry button
        val addEntryButton: Button = findViewById(R.id.addEntryButton)
        addEntryButton.setOnClickListener{
            onAddEntryButtonClick()
        }
    }


    fun onAddEntryButtonClick(){
        val secondActivityIntent: Intent = Intent(this, AddActivity::class.java)
        startActivityForResult(secondActivityIntent, 100)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == 100 && resultCode == RESULT_OK)
        {
            data?.let {
                val returnedStringValue: String = data.getStringExtra("value")!!

            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}