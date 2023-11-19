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

        binding.feedback.setOnClickListener {
            val intent = Intent(Intent.ACTION_SEND)

            val emailsend = "abc@gmail.com"
            val emailsubject = "Feedback: NutriSys"

            intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(emailsend))
            intent.putExtra(Intent.EXTRA_SUBJECT, emailsubject)

            intent.type = "message/rfc822"

            Toast.makeText(context?.applicationContext, "Feedback", Toast.LENGTH_SHORT).show()

            startActivity(Intent.createChooser(intent, "Choose an Email client :"))

        }

        binding.faq.setOnClickListener {
            val intent = Intent(context!!.applicationContext, DetailProfileFaqActivity::class.java)
            Toast.makeText(context?.applicationContext, "FAQ", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }

        binding.aboutUs.setOnClickListener {
            val intent = Intent(context!!.applicationContext, DetailProfileAboutusActivity::class.java)
            Toast.makeText(context?.applicationContext, "About Us", Toast.LENGTH_SHORT).show()
            startActivity(intent)
        }

        return binding.root
    }


}