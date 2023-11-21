package com.nutrisysteam.nutrisys

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.nutrisysteam.nutrisys.api.RetrofitInstance

import com.nutrisysteam.nutrisys.databinding.FragmentFitnessBinding
import com.nutrisysteam.nutrisys.models.Exercise
import com.nutrisysteam.nutrisys.models.ExerciseItem
import com.nutrisysteam.nutrisys.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FitnessFragment : Fragment() {

    private var _binding: FragmentFitnessBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        _binding = FragmentFitnessBinding.inflate(inflater, container, false)


        // Navigating to WeeklyExercise Activity
        binding.calisthenicsCard.setOnClickListener {
            val intent = Intent(context!!.applicationContext, WeeklyExerciseActivity::class.java)
            intent.putExtra(WeeklyExerciseActivity.EXERCISE, WeeklyExerciseActivity.CALISTHENICS)
            startActivity(intent)
        }
        binding.weightLossCard.setOnClickListener {
            val intent = Intent(context!!.applicationContext, WeeklyExerciseActivity::class.java)
            intent.putExtra(WeeklyExerciseActivity.EXERCISE, WeeklyExerciseActivity.WEIGHT_LOSS)
            startActivity(intent)
        }
        binding.muscleGainCard.setOnClickListener {
            val intent = Intent(context!!.applicationContext, WeeklyExerciseActivity::class.java)
            intent.putExtra(WeeklyExerciseActivity.EXERCISE, WeeklyExerciseActivity.MUSCLE_GAIN)
            startActivity(intent)
        }

        // calling the functions to get Data fromm API
        getCalisthenicsDataFromAPI()
        getMuscleGainDataFromAPI()
        getWeightLossDataFromAPI()


        return binding.root
    }

    // functions to fetch Exercise Data From API
    private fun getCalisthenicsDataFromAPI() {
        RetrofitInstance.api.getCalisthenicsData().enqueue(object : Callback<Exercise> {

            override fun onResponse(call: Call<Exercise>, response: Response<Exercise>) {
                if (response.isSuccessful) {
                    Log.e("MY_TAG", "Calisthenics Data Received ")
                    val list: ArrayList<ExerciseItem> = response.body()!!
                    Constants.setCalisthenicsList(list)
                    logData(list)
                } else {
                    Log.e("MY_TAG", "Response get")
                }
            }

            override fun onFailure(call: Call<Exercise>, t: Throwable) {
                getCalisthenicsDataFromAPI()
                Log.e("MY_TAG", "error: ${t.message.toString()}")
            }
        })
    }

    private fun getWeightLossDataFromAPI() {
        RetrofitInstance.api.getWeightLossData().enqueue(object : Callback<Exercise> {
            override fun onResponse(call: Call<Exercise>, response: Response<Exercise>) {
                if (response.isSuccessful) {
                    Log.e("MY_TAG", "Weight Loss Data Received ")
                    val list: ArrayList<ExerciseItem> = response.body()!!
                    Constants.setWeightLossList(list)
                    logData(list)
                } else {
                    Log.e("MY_TAG", "Request received")
                }
            }

            override fun onFailure(call: Call<Exercise>, t: Throwable) {
                getWeightLossDataFromAPI()
                Log.e("MY_TAG", "error: ${t.message.toString()}")
            }

        })
    }

    private fun getMuscleGainDataFromAPI() {
        RetrofitInstance.api.getMuscleGainData().enqueue(object : Callback<Exercise> {
            override fun onResponse(call: Call<Exercise>, response: Response<Exercise>) {
                if (response.isSuccessful) {
                    Log.e("MY_TAG", "Muscle Gain  Data Received ")
                    val list: ArrayList<ExerciseItem> = response.body()!!
                    Constants.setMuscleGainList(list)
                    logData(list)
                } else {
                    Log.e("MY_TAG", "Request received")
                }
            }

            override fun onFailure(call: Call<Exercise>, t: Throwable) {
                getMuscleGainDataFromAPI()
                Log.e("MY_TAG", "error: ${t.message.toString()}")
            }

        })
    }

    fun logData(list: ArrayList<ExerciseItem>) {
        for(i in list) {
            Log.e("DATA",i._id)
            Log.e("DATA", i.NAME)
            Log.e("DATA",i.CAL_INSTRUCTION)
            Log.e("DATA",i.PRIM_MUSCLE)
            Log.e("DATA",i.CATEGORY)
            Log.e("DATA",i.DAY_OF_THE_WEEK)
            Log.e("DATA",i.URL_1)
            Log.e("DATA",i.URL_2)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}