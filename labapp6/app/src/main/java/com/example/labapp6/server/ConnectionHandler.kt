package com.example.labapp6.server

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.net.Socket
import java.nio.ByteBuffer


class ConnectionHandler(private val applicationContext: Context,private val clientSocket: Socket) : Runnable {
    var xAxis: Float = 0.0f
    var yAxis: Float = 0.0f
    var zAxis: Float = 0.0f

    override fun run() {
        val clientIP = clientSocket.getInetAddress().getHostAddress()
        val clientPort = clientSocket.getPort().toString()
        Log.i("Client", clientIP + ":" + clientPort)

        val sensorManager  = applicationContext.getSystemService(AppCompatActivity.SENSOR_SERVICE) as SensorManager
        val sensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY)

        val listener: SensorEventListener = object :  SensorEventListener {
            override fun onSensorChanged(e: SensorEvent) {
                if (e.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
                    xAxis = e.values[0]
                    yAxis = e.values[1]
                    zAxis = e.values[2]
                }
            }
            override fun onAccuracyChanged(p0: Sensor?, p1: Int) {}
        }
        sensorManager.registerListener(listener, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_UI)

        while(true) {
            try {
                val byteBuffer: ByteBuffer = ByteBuffer.allocate(12)
                byteBuffer.putFloat(xAxis)
                byteBuffer.putFloat(yAxis)
                byteBuffer.putFloat(zAxis)
                Log.i("X axis", xAxis.toString())
                Log.i("Y axis", yAxis.toString())
                Log.i("Z axis", zAxis.toString())
                val byteArray = byteBuffer.array()
                clientSocket.getOutputStream().write(byteArray)
                Thread.sleep(500)
            } catch (e: java.net.SocketException){
                e.printStackTrace()
                break
            }

        }
    }

}