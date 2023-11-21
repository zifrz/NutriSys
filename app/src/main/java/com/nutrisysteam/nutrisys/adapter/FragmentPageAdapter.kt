package com.nutrisysteam.nutrisys.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.nutrisysteam.nutrisys.Exercise1Fragment
import com.nutrisysteam.nutrisys.Exercise2Fragment

class FragmentPageAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    private val category: String,
    private val exerciseOne: String,
    private val exerciseTwo: String,
) : FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            Exercise1Fragment.newInstance(exerciseOne,category)
        } else {
            Exercise2Fragment.newInstance(exerciseTwo,category)
        }
    }

}