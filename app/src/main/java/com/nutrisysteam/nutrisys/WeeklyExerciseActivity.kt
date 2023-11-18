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
        setWeeklyData(value.toString())



    }

    private fun setWeeklyData(value : String) {
        if(value== CALISTHENICS) {
            binding.monEx.text = getString(R.string.cal_mon)
            binding.tueEx.text = getString(R.string.cal_tue)
            binding.wedEx.text = getString(R.string.cal_wed)
            binding.thruEx.text = getString(R.string.cal_thu)
            binding.friEx.text = getString(R.string.cal_fir)
            binding.satEx.text = getString(R.string.cal_sat)
            binding.sunEx.text = getString(R.string.cal_sun)
        }
        if(value== WEIGHT_LOSS) {
            binding.monEx.text = getString(R.string.wl_mon)
            binding.tueEx.text = getString(R.string.wl_tue)
            binding.wedEx.text = getString(R.string.wl_wed)
            binding.thruEx.text = getString(R.string.wl_thu)
            binding.friEx.text = getString(R.string.wl_fir)
            binding.satEx.text = getString(R.string.wl_sat)
            binding.sunEx.text = getString(R.string.wl_sun)
        }
        if(value== MUSCLE_GAIN) {
            binding.monEx.text = getString(R.string.mg_mon)
            binding.tueEx.text = getString(R.string.mg_tue)
            binding.wedEx.text = getString(R.string.mg_wed)
            binding.thruEx.text = getString(R.string.mg_thu)
            binding.friEx.text = getString(R.string.mg_fir)
            binding.satEx.text = getString(R.string.mg_sat)
            binding.sunEx.text = getString(R.string.mg_sun)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}