package com.nutrisysteam.nutrisys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import com.nutrisysteam.nutrisys.databinding.ActivityDetailProfileBinding

class DetailProfileActivity : AppCompatActivity() {

    companion object {
        const val PROFILE = "PROFILE"
        const val FEEDBACK = "FEEDBACK"
        const val FAQ = "FAQ"
        const val ABOUT_US = "ABOUT_US"
    }

    private var _binding: ActivityDetailProfileBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_profile)
        _binding = ActivityDetailProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val arrayAdapter: ArrayAdapter<*>

        var listViewProfile = binding.lvDetailProfile


        val value = intent.getStringExtra(PROFILE)

        when (value) {
            "FEEDBACK" -> {
                binding.tvDetailProfileHeader.text = "Feedback"
                val dataListView = arrayOf(
                    "Let us know how we can Improve",
                    "Email: abc@gmail.com",

                    )

                arrayAdapter = ArrayAdapter(this, R.layout.detail_profile_listitem, dataListView)
                listViewProfile.adapter = arrayAdapter

                listViewProfile.setOnItemClickListener { parent, _, position, _ ->
                    val selectedItem = parent.getItemAtPosition(position) as String
//                    Log.d("MY_LIST_ITEM", "value: $selectedItem")
                    if (selectedItem == "Email: abc@gmail.com") {

                        // Mail to Maintainers
                    }
                }
            }
            "FAQ" -> {
                val dataListView = arrayOf(
                    "Q1: \nA1: ",
                    "Q2: \nA2: ",
                    "Q3: \nA3: ",
                    "Q4: \nA4: ",
                    "Q5: \nA5: ",
                    "Q6: \nA6: ",
                    "Q7: \nA7: "
                )

                arrayAdapter = ArrayAdapter(this, R.layout.detail_profile_listitem, dataListView)
                listViewProfile.adapter = arrayAdapter
                binding.tvDetailProfileHeader.text = "F.A.Q"

            }
            "ABOUT_US" -> {
                binding.tvDetailProfileHeader.text = "About Us"
            }
            else -> {
                binding.tvDetailProfileHeader.text = "Profile"
            }
        }
    }
}