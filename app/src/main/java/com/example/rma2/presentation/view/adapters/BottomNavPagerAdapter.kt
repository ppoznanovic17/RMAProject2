package com.example.rma2.presentation.view.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.rma2.presentation.view.fragments.NotesFragment
import com.example.rma2.presentation.view.fragments.ProfileFragment
import com.example.rma2.presentation.view.fragments.ScheduleFragment
import com.example.rma2.presentation.view.fragments.StatisticFragment

class BottomNavPagerAdapter(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {


    companion object{
        private const val ITEM_COUNT = 4
        const val FRAGMENT_1_SCHEDULE = 0
        const val FRAGMENT_2_NOTES = 1
        const val FRAGMENT_3_STATISTICS = 2
        const val FRAGMENT_4_PROFILE = 3
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            FRAGMENT_1_SCHEDULE -> ScheduleFragment()
            FRAGMENT_2_NOTES -> NotesFragment()
            FRAGMENT_3_STATISTICS -> StatisticFragment()
            else -> ProfileFragment()
        }
    }

    override fun getCount(): Int {
        return ITEM_COUNT
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            FRAGMENT_1_SCHEDULE -> "1"
            FRAGMENT_2_NOTES -> "2"
            FRAGMENT_3_STATISTICS -> "3"
            else -> "4"
        }
    }

}