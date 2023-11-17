package com.nutrisysteam.nutrisys

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.nutrisysteam.nutrisys.databinding.FragmentProfileBinding
import java.util.zip.Inflater

class ProfileFragment : Fragment() {

    lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProfileBinding.inflate(inflater, container, false)

//        binding.myProfile.setOnClickListener {
//            Toast.makeText(context?.applicationContext, "My Profile", Toast.LENGTH_SHORT).show()
//        }
//
//        binding.settings.setOnClickListener {
//            Toast.makeText(context?.applicationContext, "Settings", Toast.LENGTH_SHORT).show()
//        }

        binding.feedback.setOnClickListener {
            Toast.makeText(context?.applicationContext, "Feedback", Toast.LENGTH_SHORT).show()

            val intent = Intent(context!!.applicationContext, DetailProfileActivity::class.java)
            intent.putExtra(DetailProfileActivity.PROFILE, DetailProfileActivity.FEEDBACK)
            startActivity(intent)
        }

        binding.faq.setOnClickListener {
            Toast.makeText(context?.applicationContext, "FAQ", Toast.LENGTH_SHORT).show()

            val intent = Intent(context!!.applicationContext, DetailProfileActivity::class.java)
            intent.putExtra(DetailProfileActivity.PROFILE, DetailProfileActivity.FAQ)
            startActivity(intent)
        }

        binding.aboutUs.setOnClickListener {
            Toast.makeText(context?.applicationContext, "About Us", Toast.LENGTH_SHORT).show()
            val intent = Intent(context!!.applicationContext, DetailProfileActivity::class.java)
            intent.putExtra(DetailProfileActivity.PROFILE, DetailProfileActivity.ABOUT_US)
            startActivity(intent)
        }

//        binding.profileScreenText.setOnClickListener {
//            Toast.makeText(
//                context?.applicationContext, "This is Toast from Profile", Toast.LENGTH_SHORT
//            ).show()
//        }

        return binding.root
    }
}