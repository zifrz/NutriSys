package com.nutrisysteam.nutrisys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.nutrisysteam.nutrisys.databinding.ActivityWeeklyExerciseBinding
import java.util.*
import kotlin.math.log

class WeeklyExerciseActivity : AppCompatActivity() {

    companion object {
        const val EXERCISE = "EXERCISE"
        const val CALISTHENICS = "CALISTHENICS"
        const val WEIGHT_LOSS = "WEIGHT_LOSS"
        const val MUSCLE_GAIN = "MUSCLE_GAIN"
    }

    private var _binding: ActivityWeeklyExerciseBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityWeeklyExerciseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val value = intent.getStringExtra(EXERCISE)
        Log.d("MY_TAG", "value: $value")

        when(value){
            "CALISTHENICS" -> binding.screenHeaderWeeklyExercise.text = "Calisthenics"
            "WEIGHT_LOSS" -> binding.screenHeaderWeeklyExercise.text = "Weight Loss"
            "MUSCLE_GAIN" -> binding.screenHeaderWeeklyExercise.text = "Muscle Gain"
            else -> binding.screenHeaderWeeklyExercise.text = "Exercise"
        }




    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}