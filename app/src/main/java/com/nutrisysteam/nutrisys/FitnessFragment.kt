package com.nutrisysteam.nutrisys

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController

import com.nutrisysteam.nutrisys.databinding.FragmentFitnessBinding


class FitnessFragment : Fragment() {

    private var _binding: FragmentFitnessBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFitnessBinding.inflate(inflater, container, false)


        // Navigating to WeeklyExercise Activity
        binding.calisthenicsCard.setOnClickListener {
            val intent = Intent(context!!.applicationContext,WeeklyExerciseActivity::class.java)
            intent.putExtra(WeeklyExerciseActivity.EXERCISE,WeeklyExerciseActivity.CALISTHENICS)
            startActivity(intent)
        }
        binding.weightLossCard.setOnClickListener {
            val intent = Intent(context!!.applicationContext,WeeklyExerciseActivity::class.java)
            intent.putExtra(WeeklyExerciseActivity.EXERCISE,WeeklyExerciseActivity.WEIGHT_LOSS)
            startActivity(intent)
        }
        binding.muscleGainCard.setOnClickListener {
            val intent = Intent(context!!.applicationContext,WeeklyExerciseActivity::class.java)
            intent.putExtra(WeeklyExerciseActivity.EXERCISE,WeeklyExerciseActivity.MUSCLE_GAIN)
            startActivity(intent)
        }




        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}