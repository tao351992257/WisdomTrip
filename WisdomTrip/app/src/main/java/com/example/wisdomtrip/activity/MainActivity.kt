package com.example.wisdomtrip.activity

import android.Manifest
import android.annotation.TargetApi
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.wisdomtrip.R
import com.example.wisdomtrip.activity.map.MainMapActivity
import android.view.WindowManager
import androidx.annotation.RequiresApi


class MainActivity : AppCompatActivity() {

    companion object {
        const val WRITE_COARSE_LOCATION_REQUEST_CODE = 200
    }

    @TargetApi(Build.VERSION_CODES.P)
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // 延伸显示区域到刘海
        val lp = window.attributes
        lp.layoutInDisplayCutoutMode = WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_SHORT_EDGES
        window.attributes = lp
        // 设置页面全屏显示
        val decorView = window.decorView
        decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//        setContentView(R.layout.activity_main)
        initPermissions()
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            var decorView = window.decorView
            decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
        }
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
            finish()
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            WRITE_COARSE_LOCATION_REQUEST_CODE -> {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startActivity(Intent(this@MainActivity, MainMapActivity::class.java))
                } else {
                    Toast.makeText(this, "定位权限没有开启，无法使用地图应用", Toast.LENGTH_SHORT).show()
                    finish()
                }
            }
        }
    }
}
