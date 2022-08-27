package com.example.noteit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import com.example.noteit.databinding.ActivityMainBinding
import com.example.noteit.fragment.HomeFragment

class MainActivity : AppCompatActivity() {

    private var isClosed = 1
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding  = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Declaring fragment manager from making data
        // transactions using the custom fragment
        val mFragmentManager = supportFragmentManager
        val mFragmentTransaction = mFragmentManager.beginTransaction()
        val mFragment = HomeFragment()
        mFragmentTransaction.replace(R.id.frame, mFragment).commit()


        binding.imgMenu.setOnClickListener{
            showHide()
        }

    }

    private fun showHide() {
        if (isClosed == 1) {
            isClosed = 0
            binding.drawerLayout.openDrawer(GravityCompat.START)
        } else if (isClosed == 0) {
            isClosed = 1
            binding.drawerLayout.closeDrawers()
        }
    }



}