package com.example.labapp6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.labapp6.server.Server

class MainActivity : AppCompatActivity() {
    var server: Thread? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        server = Thread(Server(applicationContext))
        server!!.start()

    }
}