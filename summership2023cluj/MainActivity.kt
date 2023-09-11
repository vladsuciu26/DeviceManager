package com.example.summership2023cluj

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.summership2023cluj.databinding.ActivityMainBinding
import com.example.summership2023cluj.ui.Navigator

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        Navigator.create(this)
        Navigator.getInstance().openSplashFragment()
        setListenersForNavigationBar()
    }

    private fun setListenersForNavigationBar() {
        binding.navigationBar.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_profile -> {
                    Navigator.getInstance().openProfileFragment()
                    true
                }

                R.id.navigation_devices -> {
                    Navigator.getInstance().openDevicesFragment()
                    true
                }

                R.id.navigation_my_devices -> {
                    Navigator.getInstance().openMyDevicesFragment()
                    true
                }

                R.id.navigation_device_management -> {
                    Navigator.getInstance().openDeviceManagementFragment()
                    true
                }

                else -> false
            }
        }
    }
}