package com.example.labapp1

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var task: MyAsyncTask? = null

    val MAX_VALUE: Int = 100

    var valueSet: Int = 0
    set(newValue){
        if(field != newValue) {
            field = newValue

            val seekBar: SeekBar = findViewById(R.id.seekBar)
            seekBar.progress = field

            val editText: EditText = findViewById(R.id.editText)
            editText.setText(field.toString())
            editText.setSelection(editText.length())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // SeekBar
        val seekBar: SeekBar = findViewById(R.id.seekBar)
        seekBar.max = MAX_VALUE
        seekBar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                valueSet = seekBar.progress
            }
            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        // EditText
        val editText: EditText = findViewById(R.id.editText)
        editText.addTextChangedListener(object :TextWatcher {
            override fun afterTextChanged(s: Editable) {
                var newValue: Int = valueSet
                try{
                    newValue = Integer.parseInt(s.toString())
                }
                catch(ex: NumberFormatException) {
                }
                if (newValue in 0..MAX_VALUE) {
                    valueSet = newValue
                }
                else if (editText.text.toString() != valueSet.toString()) {
                    editText.setText(valueSet.toString())
                    editText.setSelection(editText.length())
                }
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })

        // StartButton
        val buttonStart: Button = findViewById(R.id.buttonStart)
        buttonStart.setOnClickListener { onStartButtonClick() }

        // StopButton
        val buttonStop: Button = findViewById(R.id.buttonStop)
        buttonStop.setOnClickListener { onStopButtonClick() }


        valueSet = MAX_VALUE/2
    }

    fun onStartButtonClick() {
        task?.let {}
            ?: run {
            task=MyAsyncTask()
            task!!.execute(this)
        }
    }

    fun onStopButtonClick(){
        task?.let {
            task!!.cancel(true)
        }
    }

}