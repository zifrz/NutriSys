package com.nutrisysteam.nutrisys

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.nutrisysteam.nutrisys.adapter.MyRvAdapter
import com.nutrisysteam.nutrisys.databinding.ActivityNutritionalInformationBinding
import com.nutrisysteam.nutrisys.utils.Constants

class NutritionalInformationActivity : AppCompatActivity() {

    private lateinit var binding : ActivityNutritionalInformationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNutritionalInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val list = Constants.getProductList()

        binding.nutritionsRV.layoutManager = LinearLayoutManager(this)
        val adapter = MyRvAdapter(this@NutritionalInformationActivity,list)
        binding.nutritionsRV.adapter = adapter

        for(i in list)  {
            Log.d("PRODUCT",i.productName)
            Log.d("PRODUCT",i.pURL)
            Log.d("PRODUCT",i.nutrition)
            Log.d("PRODUCT","--------------------------------------------------------------------")

        }



    }


}