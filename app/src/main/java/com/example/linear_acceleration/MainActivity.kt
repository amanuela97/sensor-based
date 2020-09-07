package com.example.linear_acceleration

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.loader.ResourcesProvider
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , SensorEventListener {

    private lateinit var sensorManager: SensorManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        if (sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION) != null) {

            Toast.makeText(applicationContext,"Success! There's a linear accelerator.", Toast.LENGTH_SHORT).show()
            val sensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION)
            sensorManager.registerListener(this,sensor,SensorManager.SENSOR_DELAY_NORMAL)

        } else {
            Toast.makeText(applicationContext,"Failure! No Linear accelerator on device.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }


    @SuppressLint("SetTextI18n")
    override fun onSensorChanged(p0: SensorEvent?) {
        if (p0?.values != null){
            Log.i("MainActivity", "X: ${p0.values[0]}, Y: ${p0.values[1]},  Z: ${p0.values[2]}")
            x_axis.text = resources.getText(R.string.x_axis).toString() + p0.values[0].toString()
            y_axis.text = resources.getText(R.string.y_axis).toString() + p0.values[1].toString()
            z_axis.text = resources.getText(R.string.z_axis).toString() + p0.values[2].toString()
        }
    }
}