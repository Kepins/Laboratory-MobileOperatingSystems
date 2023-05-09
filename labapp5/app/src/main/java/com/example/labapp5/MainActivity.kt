package com.example.labapp5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import com.example.labapp5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    var originalArray: ArrayList<Int> = ArrayList()
    var filteredArray: ArrayList<Int> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        originalArray.addAll(arrayOf(1,3,6,6,2,1,7,10,6,4,22))
        fillOriginalListView()

        // setButton
        val callButton: Button = findViewById(R.id.callButton)
        callButton.setOnClickListener {
            onCallButtonClick()
        }

        // callButton
        val resetButton: Button = findViewById(R.id.resetButton)
        resetButton.setOnClickListener {
            onResetButtonClick()
        }

        // removeDupButton
        val removeDupButton: Button = findViewById(R.id.removeDupButton)
        removeDupButton.setOnClickListener {
            onRemoveDupButtonClick()
        }
    }

    fun onCallButtonClick(){
        val c_text: TextView = findViewById(R.id.c_text)
        c_text.text = exampleString()
    }

    fun onResetButtonClick(){
        val c_text: TextView = findViewById(R.id.c_text)
        c_text.text = ""
    }

    fun onRemoveDupButtonClick(){
        removeDuplicates(originalArray.toIntArray()).toCollection(filteredArray)

        fillFilteredListView()
    }

    fun fillOriginalListView(){
        val arrayList: MutableList<String> = ArrayList();
        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList)
        val listView: ListView = findViewById(R.id.originalListView)
        for (int in originalArray){
            arrayList.add(int.toString())
        }
        listView.adapter = arrayAdapter
    }

    fun fillFilteredListView(){
        val arrayList: MutableList<String> = ArrayList();
        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList)
        val listView: ListView = findViewById(R.id.filteredListView)
        for (int in filteredArray){
            arrayList.add(int.toString())
        }
        listView.adapter = arrayAdapter
    }



    /**
     * A native method that is implemented by the 'labapp5' native library,
     * which is packaged with this application.
     */
    external fun exampleString(): String
    external fun removeDuplicates(array: IntArray): IntArray

    companion object {
        // Used to load the 'labapp5' library on application startup.
        init {
            System.loadLibrary("labapp5")
        }
    }
}