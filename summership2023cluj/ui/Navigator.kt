package com.example.summership2023cluj.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.lifecycleScope
import com.example.summership2023cluj.MainActivity
import com.example.summership2023cluj.MyApplication
import com.example.summership2023cluj.R
import com.example.summership2023cluj.ui.detail.DetailFragment
import com.example.summership2023cluj.ui.entries.EntriesFragment
import com.example.summership2023cluj.ui.devicemanagement.DeviceManagementFragment
import com.example.summership2023cluj.ui.login.LoginFragment
import com.example.summership2023cluj.ui.mydevices.MyEntriesFragment
import com.example.summership2023cluj.ui.profile.ProfileFragment
import com.example.summership2023cluj.ui.splash.SplashFragment
import kotlinx.coroutines.launch

class Navigator(mainActivity: MainActivity) : AppCompatActivity() {
    private val activity = mainActivity
    private val fragmentManager: FragmentManager = mainActivity.supportFragmentManager
    private var fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()

    companion object{
        private lateinit var instance: Navigator

        fun create(activity: MainActivity){
            instance = Navigator(activity)
        }


        fun getInstance(): Navigator {
            return instance
        }
    }

    private fun beginTransaction() {
        fragmentTransaction = fragmentManager.beginTransaction()
    }

    fun openSplashFragment() {
        fragmentTransaction.add(R.id.fragmentHolder, SplashFragment()).commit()
    }

    fun openLoginFragment() {
        beginTransaction()
        fragmentTransaction.replace(R.id.fragmentHolder, LoginFragment()).commit()
    }

    fun openProfileFragment() {
        beginTransaction()
        fragmentTransaction.replace(R.id.fragmentHolder, ProfileFragment()).commit()
        setNavigationBarVisible()
    }

   fun setNavigationBarVisible() {
       activity.binding.navigationBar.visibility = View.VISIBLE
       val deviceManagementItem = activity.binding.navigationBar.menu.getItem(3)
       lifecycleScope.launch {
           val isAdmin = MyApplication.dataStore.getProfile().admin
           deviceManagementItem?.isVisible = isAdmin == 1
       }
    }

    fun openDevicesFragment() {
        beginTransaction()
        fragmentTransaction.replace(R.id.fragmentHolder, EntriesFragment())
        fragmentTransaction.commit()

        if (activity.binding.navigationBar.visibility == View.INVISIBLE) {
            activity.binding.navigationBar.visibility = View.VISIBLE
        }
    }

    fun openMyDevicesFragment() {
        beginTransaction()
        fragmentTransaction.replace(R.id.fragmentHolder, MyEntriesFragment())
        fragmentTransaction.commit()
    }

    fun openDeviceManagementFragment() {
        beginTransaction()
        fragmentTransaction.replace(R.id.fragmentHolder, DeviceManagementFragment())
        fragmentTransaction.commit()
    }

    fun detailPageFragment(idOfEntry: Int) {
        beginTransaction()
        val detailFragment = constructDetailFragment(idOfEntry)
        fragmentTransaction.replace(R.id.fragmentHolder, detailFragment)
        fragmentTransaction.commit()
        activity.binding.navigationBar.visibility = View.INVISIBLE
    }

    private fun constructDetailFragment(idOfEntry: Int): DetailFragment {
        val bundle = Bundle()
        bundle.putInt("entry_id", idOfEntry)

        val detailFragment = DetailFragment()
        detailFragment.arguments = bundle

        return detailFragment
    }
}