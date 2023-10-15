package com.nutrisysteam.nutrisys

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.nutrisysteam.nutrisys.databinding.FragmentProfileBinding
import java.util.zip.Inflater

class ProfileFragment : Fragment() {

    lateinit var binding : FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProfileBinding.inflate(inflater, container, false)


        binding.profileScreenText.setOnClickListener {

            Toast.makeText(context?.applicationContext, "This is Toast from Profile", Toast.LENGTH_SHORT).show()
        }
        return binding.root
    }


}