package com.nutrisysteam.nutrisys

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.nutrisysteam.nutrisys.databinding.FragmentNutrtionBinding


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class NutritionFragment : Fragment() {

    private var _binding: FragmentNutrtionBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentNutrtionBinding.inflate(inflater, container, false)
        return binding.root

    }

}