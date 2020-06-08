package com.example.rma2.presentation.view.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rma2.R
import com.example.rma2.presentation.view.adapters.BottomNavPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import androidx.viewpager.widget.PagerAdapter


class MainActivity : AppCompatActivity(R.layout.activity_main){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewPager()
        initNavigation()
    }


    private fun initViewPager(){
        viewPager.adapter = BottomNavPagerAdapter(supportFragmentManager)
    }

    private fun initNavigation(){
        bottomMenu.setOnNavigationItemSelectedListener {
            when(it.itemId){
                 R.id.schedule -> {
                    viewPager.setCurrentItem(BottomNavPagerAdapter.FRAGMENT_1_SCHEDULE)
                }
                R.id.notes -> {
                    viewPager.setCurrentItem(BottomNavPagerAdapter.FRAGMENT_2_NOTES, false)
                }
                R.id.statistics -> {
                    viewPager.setCurrentItem(BottomNavPagerAdapter.FRAGMENT_3_STATISTICS, false)
                }
                R.id.profile -> {
                    viewPager.setCurrentItem(BottomNavPagerAdapter.FRAGMENT_4_PROFILE, false)
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
    }



}