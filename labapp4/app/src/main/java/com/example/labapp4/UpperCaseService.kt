package com.example.labapp4

import android.app.Service
import android.content.Intent
import android.os.Handler

import android.os.IBinder
import android.os.Message

import android.os.Messenger
import android.util.Log



class UpperCaseService : Service() {
    // Klasa obsługująca wiadomości otrzymane od klienta.
    internal inner class IncomingHandler : Handler() {
        override fun handleMessage(msg: Message) {
            // Tu należy obsłużyć wiadomość msg w zależności od jej parametru „what”.
            if (msg.what == 1){
                val returnValue: String = msg.data.getString("value", "").uppercase()
                val message: Message = Message.obtain(null, 1, 0, 0)
                message.data.putCharSequence("value", returnValue)

                val replyTo: Messenger = msg.replyTo
                replyTo.send(message) // Serwis
            }
        }
    }

    // Obiekt Messenger służący do odbierania wiadomości od klienta.
    val mMessenger: Messenger = Messenger(IncomingHandler())

    // Zwrócenie interfejsu obiektu Messengera podczas zestawienia połączenia
    // pomiędzy klientem a serwisem.
    override fun onBind(intent: Intent?): IBinder {
        return mMessenger.binder
    }
}