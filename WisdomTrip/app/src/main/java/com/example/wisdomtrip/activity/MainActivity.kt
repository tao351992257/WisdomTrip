package com.example.wisdomtrip.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.wisdomtrip.R
import com.example.wisdomtrip.activity.map.MainMapActivity
import java.util.*

class MainActivity : AppCompatActivity() {
    private var timerIntent: Timer? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        startActivity(Intent(this@MainActivity, MainMapActivity::class.java))
        timerIntent = Timer()
        timerIntent?.schedule(object : TimerTask() {
            override fun run() {
                Log.d("TAG", "Timer")
            }
        }, 0, 5000L)
    }

    override fun onDestroy() {
        super.onDestroy()
        timerIntent?.cancel()
        timerIntent = null
    }
}
