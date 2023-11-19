package com.nutrisysteam.nutrisys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import com.nutrisysteam.nutrisys.databinding.ActivityDetailExerciseBinding
import java.text.DecimalFormat
import kotlin.math.roundToInt

class DetailExerciseActivity : AppCompatActivity() {

    private var _binding : ActivityDetailExerciseBinding? = null
    private val binding get() = _binding!!

    private val countdownTime = 120 // 60 seconds
    private val clockTime = (countdownTime * 1000).toLong()
    private val progressTime = (clockTime / 1000).toFloat()

    private var customCountdownTimer: CustomCountdownTimer? = null

    private var isRunning = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailExerciseBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        val exerciseCategory = intent.getStringExtra(WeeklyExerciseActivity.EXERCISE)
        val dayOfWeek = intent.getStringExtra(WeeklyExerciseActivity.DAY_OF_WEEK)

        Log.e("MY_TAG","exercise Category: $exerciseCategory")
        Log.e("MY_TAG","day of week: $dayOfWeek")

        when(exerciseCategory){
            "CALISTHENICS" -> binding.screenHeaderWeeklyExercise.text = "Calisthenics"
            "WEIGHT_LOSS" -> binding.screenHeaderWeeklyExercise.text = "Weight Loss"
            "MUSCLE_GAIN" -> binding.screenHeaderWeeklyExercise.text = "Muscle Gain"
            else -> binding.screenHeaderWeeklyExercise.text = "Exercise"
        }

        binding.startBtn.setOnClickListener {
            if(!isRunning) {
                var secondsLeft = 0
                customCountdownTimer = object : CustomCountdownTimer(clockTime, 1000) {}
                customCountdownTimer?.onTick = { millisUntilFinished ->
                    val second = (millisUntilFinished / 1000.0f).roundToInt()
                    if (second != secondsLeft) {
                        secondsLeft = second
                        timerFormat(secondsLeft, binding.timeTxt)
                    }

                }
                customCountdownTimer?.onFinish = {
                    timerFormat(0, binding.timeTxt)
                }

                binding.circularProgressBar.max = progressTime.toInt()

                binding.circularProgressBar.progress = progressTime.toInt()
                customCountdownTimer?.startTimer()
                isRunning = true
            }
        }

        binding.pauseBtn.setOnClickListener {
            customCountdownTimer?.pauseTimer()
        }

        binding.resumeBtn.setOnClickListener {
            customCountdownTimer?.resumeTimer()
        }

        binding.resetBtn.setOnClickListener {
            binding.circularProgressBar.progress = progressTime.toInt()
            customCountdownTimer?.restartTimer()
        }

    }

    private fun timerFormat(secondsLeft: Int, timeTxt: TextView) {
        binding.circularProgressBar.progress = secondsLeft
        val decimalFormat = DecimalFormat("00")
        val hour = secondsLeft / 3600
        val min = (secondsLeft % 3600) / 60
        val seconds = secondsLeft % 60

        val timeFormat2 = decimalFormat.format(min) + ":" + decimalFormat.format(seconds)
        timeTxt.text = timeFormat2
    }

    private fun onBackPressedMethod() {
        customCountdownTimer?.destroyTimer()
        finish()
    }

    override fun onPause() {
        customCountdownTimer?.pauseTimer()
        super.onPause()
    }

    override fun onResume() {
        super.onResume()
        customCountdownTimer?.resumeTimer()
    }


    override fun onDestroy() {
        super.onDestroy()
        customCountdownTimer?.destroyTimer()
        _binding = null
    }
}