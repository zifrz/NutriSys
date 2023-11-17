package com.nutrisysteam.nutrisys.api

import com.nutrisysteam.nutrisys.models.Exercise
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {

    @GET("exercises/CALISTHENICS")
    fun getCalisthenicsData() : Call<Exercise>

    @GET("exercises/WEIGHT LOSS")
    fun getDataWeightLossData() : Call<Exercise>

    @GET("exercises/MUSCLE GAIN")
    fun getMuscleGainData() : Call<Exercise>

}