package com.example.labapp4

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.*
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class UseServiceActivity: AppCompatActivity() {
    var isBound: Boolean = false
    var mMessenger: Messenger? = null

    val serviceConnection: ServiceConnection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName, service: IBinder) {
            isBound = true
            mMessenger = Messenger(service)
        }

        override fun onServiceDisconnected(name: ComponentName) {
            mMessenger = null
            isBound = false
        }
    }

    internal inner class ReturningHandler : Handler() {
        override fun handleMessage(msg: Message) {
            if (msg.what == 1){
                val textViewUse: TextView = findViewById(R.id.textViewUse)
                textViewUse.text = msg.data.getString("value")!!
            }
        }
    }
    val retmMessenger = Messenger(ReturningHandler())


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_use_service)

        // sendButton
        val sendButton: Button = findViewById(R.id.sendButton)
        sendButton.setOnClickListener{
            onSendButtonClick()
        }

        // backButton
        val backButton: Button = findViewById(R.id.backButton)
        backButton.setOnClickListener{
            onBackButtonClick()
        }


        val messenger = Intent()
        messenger.setClassName("com.example.labapp4", "com.example.labapp4.UpperCaseService")
        try {
            bindService(messenger, serviceConnection, BIND_EXTERNAL_SERVICE)
        }
        catch (e: SecurityException) {
            isBound = false
            mMessenger = null
        }


    }

    fun onSendButtonClick(){
        val editText: EditText = findViewById(R.id.editText)
        val editTextStr: String = editText.text.toString()

        if (isBound) {
            val msg: Message = Message.obtain(null, 1, 0, 0)
            msg.data.putCharSequence("value", editTextStr)
            msg.replyTo = retmMessenger
            try {
                mMessenger!!.send(msg)
            } catch (e: RemoteException) {
                e.printStackTrace()
            }
        }
        else {
            val textViewUse: TextView = findViewById(R.id.textViewUse)
            textViewUse.text = "Service inactive"
        }

    }

    fun onBackButtonClick(){
        finish()
    }

    override fun onDestroy() {
        if (isBound){
            unbindService(serviceConnection)
        }
        super.onDestroy()
    }



}