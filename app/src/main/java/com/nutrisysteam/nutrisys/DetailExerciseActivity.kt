package com.nutrisysteam.nutrisys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.nutrisysteam.nutrisys.databinding.ActivityDetailExerciseBinding

class DetailExerciseActivity : AppCompatActivity() {

    private var _binding : ActivityDetailExerciseBinding? = null
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailExerciseBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        val exerciseCategory = intent.getStringExtra(WeeklyExerciseActivity.EXERCISE)
        val dayOfWeek = intent.getStringExtra(WeeklyExerciseActivity.DAY_OF_WEEK)

        Log.e("MY_TAG","exercise Category: $exerciseCategory")
        Log.e("MY_TAG","day of week: $dayOfWeek")

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}