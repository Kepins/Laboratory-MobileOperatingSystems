package com.example.labapp6.server

import android.app.Application
import android.content.Context
import android.util.Log
import java.net.InetAddress
import java.net.ServerSocket

class Server(val applicationContext: Context) : Runnable {
    val PORT: Int = 43560

    override fun run() {
        try {
            val ss = ServerSocket(PORT, 0, InetAddress.getByName("127.0.0.1"))
            Log.i("LocalPort", "" + ss.getLocalPort())
            Log.i("HostAddress", ss.getInetAddress().getHostAddress())
            Log.i("SocketAddress", ss.getLocalSocketAddress().toString())
            while(true) {
                val clientSocket = ss.accept()
                val ConnectionHandlerThread = Thread(ConnectionHandler(applicationContext ,clientSocket))
                ConnectionHandlerThread.start()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}