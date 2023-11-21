package com.nutrisysteam.nutrisys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.nutrisysteam.nutrisys.adapter.FragmentPageAdapter
import com.nutrisysteam.nutrisys.databinding.ActivityDetailExerciseBinding
import com.nutrisysteam.nutrisys.models.ExerciseItem
import com.nutrisysteam.nutrisys.utils.Constants
import java.text.DecimalFormat
import kotlin.math.roundToInt

class DetailExerciseActivity : AppCompatActivity() {

    private var _binding : ActivityDetailExerciseBinding? = null
    private val binding get() = _binding!!

    private var id : ArrayList<String> = arrayListOf()

    private lateinit var adapter: FragmentPageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val exerciseCategory = intent.getStringExtra(WeeklyExerciseActivity.EXERCISE)
        val dayOfWeek = intent.getStringExtra(WeeklyExerciseActivity.DAY_OF_WEEK)

        Log.e("MY_TAG","exercise Category: $exerciseCategory")
        Log.e("MY_TAG","day of week: $dayOfWeek")


        solve(exerciseCategory.toString(),dayOfWeek.toString())

        if(id.isEmpty()) {
//            Toast.makeText(this, "Some thing went wrong", Toast.LENGTH_SHORT).show()
            adapter = FragmentPageAdapter(supportFragmentManager, lifecycle,exerciseCategory.toString(),"","")
        }
        else if(id.size==2) {
            adapter = FragmentPageAdapter(supportFragmentManager, lifecycle,exerciseCategory.toString(),id[0],id[1])
        }else {
            adapter = FragmentPageAdapter(supportFragmentManager, lifecycle,exerciseCategory.toString(),"","")
        }

        val exerciseOne = setExerciseName(exerciseCategory.toString(), id[0])
        val exerciseTwo = setExerciseName(exerciseCategory.toString(),id[1])

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(exerciseOne))
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(exerciseTwo))

        binding.viewPager2.adapter = adapter

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                if (tab != null) {
                    binding.viewPager2.currentItem = tab.position
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

        binding.viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.tabLayout.selectTab(binding.tabLayout.getTabAt(position))
            }
        })


    }

    private fun setExerciseName(exeCat: String, id : String): String {
        if(exeCat=="CALISTHENICS") {
            val l = Constants.getCalisthenicsList()
            for( i in l) {
                if(i._id == id) {
                    return i.NAME
                }
            }
        }
        else if (exeCat == "WEIGHT_LOSS") {
            val l = Constants.getWeightLossList()
            for( i in l) {
                if(i._id == id) {
                    return i.NAME
                }
            }
        }
        else if (exeCat == "MUSCLE_GAIN") {
            val l = Constants.getMuscleGainList()
            for( i in l) {
                if(i._id == id) {
                    return i.NAME
                }
            }
        }
        return "Exercise"
    }

    private fun solve(exeCat: String, day : String) {
        if(exeCat == "CALISTHENICS" ) {

            val list : ArrayList<ExerciseItem> = Constants.getCalisthenicsList()

            if(list.isEmpty()) {
                Toast.makeText(this@DetailExerciseActivity, "Fail To Load Data", Toast.LENGTH_SHORT).show()
            }
            else {
                when(day) {
                    "MONDAY" -> {
                        id = getID(list,"Monday")
                    }
                    "TUESDAY"-> {
                        id = getID(list,"Tuesday")
                    }
                    "WEDNESDAY" -> {
                        id = getID(list,"Wednesday")
                    }
                    "THURSDAY" -> {
                        id = getID(list,"Thursday")
                    }
                    "FRIDAY" -> {
                        id = getID(list,"Friday")
                        Log.e(":-)","friday: ${id.size} ")
                        if(id.size==1) {
                            id.add("6556317ef7044a0446b691be")
                        }
                    }
                    "SATURDAY" -> {
                        id = getID(list,"Saturday")
                    }
                    "SUNDAY" -> {
                        id = getID(list,"Sunday")
                    }
                }
            }
        }
        else if(exeCat == "WEIGHT_LOSS") {
            val list : ArrayList<ExerciseItem> = Constants.getWeightLossList()

            if(list.isEmpty()) {
                Toast.makeText(this@DetailExerciseActivity, "Fail To Load Data", Toast.LENGTH_SHORT).show()
            }
            else {
                when(day) {
                    "MONDAY" -> {
                        id = getID(list,"Monday")
                    }
                    "TUESDAY"-> {
                        id = getID(list,"Tuesday")
                    }
                    "WEDNESDAY" -> {
                        id = getID(list,"Wednesday")
                    }
                    "THURSDAY" -> {
                        id = getID(list,"Thursday")
                    }
                    "FRIDAY" -> {
                        id = getID(list,"Friday")
                        if(id.size==1) {
                            id.add("6556322bf7044a0446b691da")
                        }
                    }
                    "SATURDAY" -> {
                        id = getID(list,"Saturday")
                    }
                    "SUNDAY" -> {
                        id = getID(list,"Sunday")
                    }
                }
            }
        }
        else if (exeCat == "MUSCLE_GAIN") {
            val list : ArrayList<ExerciseItem> = Constants.getMuscleGainList()

            if(list.isEmpty()) {
                Toast.makeText(this@DetailExerciseActivity, "Fail To Load Data", Toast.LENGTH_SHORT).show()
            }
            else {
                when(day) {
                    "MONDAY" -> {
                        id = getID(list,"Monday")
                    }
                    "TUESDAY"-> {
                        id = getID(list,"Tuesday")
                    }
                    "WEDNESDAY" -> {
                        id = getID(list,"Wednesday")
                    }
                    "THURSDAY" -> {
                        id = getID(list,"Thursday")
                    }
                    "FRIDAY" -> {
                        id = getID(list,"Friday")
                        if(id.size==1) {
                            id.add("65563218f7044a0446b691cc")
                        }
                    }
                    "SATURDAY" -> {
                        id = getID(list,"Saturday")
                    }
                    "SUNDAY" -> {
                        id = getID(list,"Sunday")
                    }
                }
            }
        }
    }


    private fun getID(list : ArrayList<ExerciseItem>,day: String) : ArrayList<String> {

        val myList : ArrayList<String> = ArrayList()

        for( i in list) {
            if(i.DAY_OF_THE_WEEK == day) {
                myList.add(i._id)
            }
        }
        Toast.makeText(this, "Some thing went wrong", Toast.LENGTH_SHORT).show()
        return myList
    }

    override fun onDestroy() {
        super.onDestroy()

        _binding = null
    }
}