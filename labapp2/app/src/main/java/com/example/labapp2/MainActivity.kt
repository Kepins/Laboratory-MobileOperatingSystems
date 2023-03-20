package com.example.labapp2

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //changeButton
        val changeButton: Button = findViewById(R.id.changeButton)
        changeButton.setOnClickListener { onChangeButtonClick() }

        //photoButton
        val photoButton: Button = findViewById(R.id.photoButton)
        photoButton.setOnClickListener { onPhotoButtonClick() }
    }


    private fun onPhotoButtonClick(){
        val imageCaptureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(imageCaptureIntent, 616)
    }

    private fun onChangeButtonClick() {
        val editText: EditText = findViewById(R.id.editText)
        val intValue: Int = Integer.parseInt(editText.text.toString())
        val secondActivityIntent: Intent = Intent(this, SecondActivity::class.java).apply {
            putExtra("value", intValue)
        }
        startActivityForResult(secondActivityIntent, 100)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == 100 && resultCode == RESULT_OK)
        {
            data?.let {
                val returnedIntValue: Int = data.getIntExtra("value", 0)
                val editText: EditText = findViewById(R.id.editText)
                editText.setText(returnedIntValue.toString())
            }
        }
        if (requestCode == 616 && resultCode == RESULT_OK)
        {
            data?.let {
                val extras = data.extras
                val imageBitmap = extras!!["data"] as Bitmap?
                val imageView: ImageView = findViewById(R.id.imageView)
                imageView.setImageBitmap(imageBitmap)
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}