package com.elkfrawy.pixaby.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.elkfrawy.pixaby.R
import com.elkfrawy.pixaby.databinding.ActivityMainBinding
import com.elkfrawy.pixaby.presentation.photo.PhotoFragment
import com.elkfrawy.pixaby.presentation.video.VideoFragment
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadFragment(PhotoFragment::class.java)
        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when(tab!!.position){
                    1 -> loadFragment(VideoFragment::class.java)
                    else -> loadFragment(PhotoFragment::class.java)
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    fun loadFragment(fragment: Class<out Fragment>) {
        supportFragmentManager.beginTransaction()
            .replace(binding.fragmentContainerView.id, fragment, null).commit()

    }
}