package com.nutrisysteam.nutrisys.api

import com.nutrisysteam.nutrisys.models.Exercise
import com.nutrisysteam.nutrisys.models.Nutritions
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {

    @GET("exercises/CALISTHENICS")
    fun getCalisthenicsData() : Call<Exercise>

    @GET("exercises/WEIGHT LOSS")
    fun getWeightLossData() : Call<Exercise>

    @GET("exercises/MUSCLE GAIN")
    fun getMuscleGainData() : Call<Exercise>

    @GET("nutritions/{product}")
    fun getData(@Path("product") product : String) : Call<Nutritions>
}