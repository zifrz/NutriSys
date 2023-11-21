package com.nutrisysteam.nutrisys

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.nutrisysteam.nutrisys.databinding.FragmentExercise1Binding
import com.nutrisysteam.nutrisys.models.ExerciseItem
import com.nutrisysteam.nutrisys.utils.Constants
import java.text.DecimalFormat
import kotlin.math.roundToInt


class Exercise1Fragment : Fragment() {

    private var _binding: FragmentExercise1Binding? = null
    private val binding get() = _binding!!


    private val countdownTime = 120 // 60 seconds
    private val clockTime = (countdownTime * 1000).toLong()
    private val progressTime = (clockTime / 1000).toFloat()

    private var customCountdownTimer: CustomCountdownTimer? = null

    private var isRunning = false

    companion object {

        private const val EXERCISE_ONE = "EXERCISE_ONE"
        private const val CATEGORY = "CATEGORY"

        fun newInstance(data: String, category: String): Exercise1Fragment {
            val fragment = Exercise1Fragment()
            val args = Bundle()
            args.putString(EXERCISE_ONE, data)
            args.putString(CATEGORY, category)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentExercise1Binding.inflate(inflater,container,false)


        val id: String? = arguments?.getString(EXERCISE_ONE)
        val category: String = arguments?.getString(CATEGORY).toString()

        Log.e("MY_TAG", "name: $id :-) ")
        Log.e("MY_TAG", "category: $category ")


        val data = getData(id.toString(),category)

        if(data == null) {
            Toast.makeText(context?.applicationContext, "NULL Data", Toast.LENGTH_SHORT).show()
        }
        else {
            Log.e("MY_TAG", " ${data.NAME}")
            Log.e("MY_TAG", " ${data.CATEGORY}")
            Log.e("MY_TAG", " ${data.DAY_OF_THE_WEEK}")
            Log.e("MY_TAG", " ${data.PRIM_MUSCLE}")
            Log.e("MY_TAG", " ${data.CAL_INSTRUCTION}")
            Log.e("MY_TAG", " ${data.URL_1}")
            Log.e("MY_TAG", " ${data.URL_2}")

            binding.exerciseName.text = data.NAME
            binding.primeMuscle.text = "Prime Muscle: " + data.PRIM_MUSCLE
            binding.exeDescription.text = "Instructions\n" + data.CAL_INSTRUCTION

            Glide.with(this@Exercise1Fragment)
                .load(data.URL_1)
                .into(binding.exerciseOne)

            Glide.with(this@Exercise1Fragment)
                .load(data.URL_2)
                .into(binding.exerciseTwo)
        }


        binding.startBtn.setOnClickListener {
            if (!isRunning) {
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


        return binding.root
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

    private fun getData(id: String, category: String): ExerciseItem? {
        if (category == "CALISTHENICS") {
            val list = Constants.getCalisthenicsList()
            for (i in list) {
                if (i._id == id) {
                    return i
                }
            }
        } else if (category == "WEIGHT_LOSS") {
            val list = Constants.getWeightLossList()
            for (i in list) {
                if (i._id == id) {
                    return i
                }
            }
        } else if (category == "MUSCLE_GAIN") {
            val list = Constants.getMuscleGainList()
            for (i in list) {
                if (i._id == id) {
                    return i
                }
            }
        }
        return null
    }


}