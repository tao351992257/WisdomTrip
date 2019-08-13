package com.example.wisdomtrip.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.wisdomtrip.R
import com.example.wisdomtrip.activity.map.MainMapActivity

class MainActivity : AppCompatActivity() {

    companion object {
        const val WRITE_COARSE_LOCATION_REQUEST_CODE = 200
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initPermissions()
    }

    private fun initPermissions() {

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                WRITE_COARSE_LOCATION_REQUEST_CODE
            )
        } else {
            startActivity(Intent(this@MainActivity, MainMapActivity::class.java))
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            WRITE_COARSE_LOCATION_REQUEST_CODE -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startActivity(Intent(this@MainActivity, MainMapActivity::class.java))
                } else {
                    Toast.makeText(this,"定位权限没有开启，无法使用地图应用",Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }
}
