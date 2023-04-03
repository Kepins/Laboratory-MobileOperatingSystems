package com.example.labapp3

import android.content.Intent
import android.content.SharedPreferences
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

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
        updateTextView()
        updateListView()
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
                db.execSQL("INSERT INTO lab3(data) VALUES ('%s');".format(returnedStringValue))

                val sdf = SimpleDateFormat("dd.MM.yyyy hh:mm:ss")
                val currentDate: String = sdf.format(Date())

                val preferences: SharedPreferences = getSharedPreferences("db-preferences", MODE_PRIVATE)
                val editor: SharedPreferences.Editor = preferences.edit()
                editor.putString("mod-date", currentDate)
                editor.apply()

                updateTextView()
                updateListView()
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }


    fun updateListView(){
        val arrayList: MutableList<String> = ArrayList();
        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList)
        val listView: ListView = findViewById(R.id.listView)
        val c: Cursor = db.rawQuery("SELECT data FROM lab3", null)
        while(c.moveToNext())
            arrayList.add(c.getString(0))
        c.close()
        listView.adapter = arrayAdapter
    }

    fun updateTextView() {
        val preferences: SharedPreferences = getSharedPreferences("db-preferences", MODE_PRIVATE)

        val textView: TextView = findViewById(R.id.textView)
        val modDate: String? = preferences.getString("mod-date", null)

        modDate?.let {
            textView.text = modDate
        } ?: run{
            textView.text = "Never modified!"
        }
    }

    override fun onDestroy() {
        db.close()
        super.onDestroy()
    }
}