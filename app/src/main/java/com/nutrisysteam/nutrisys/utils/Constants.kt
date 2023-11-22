package com.nutrisysteam.nutrisys.utils

import com.nutrisysteam.nutrisys.models.ExerciseItem
import com.nutrisysteam.nutrisys.models.NutritionsItem
import com.nutrisysteam.nutrisys.models.Product

object Constants {

    //  ------------------------- Calisthenics -------------------------
    private var calisthenicsList: ArrayList<ExerciseItem> = ArrayList()
    fun getCalisthenicsList(): ArrayList<ExerciseItem> {
        return this.calisthenicsList
    }

    fun setCalisthenicsList(calisthenics: ArrayList<ExerciseItem>) {
        this.calisthenicsList = calisthenics
    }

    //  ------------------------- Weight Loss -------------------------
    private var weightLossList: ArrayList<ExerciseItem> = ArrayList()
    fun getWeightLossList(): ArrayList<ExerciseItem> {
        return this.weightLossList
    }

    fun setWeightLossList(weightLossList: ArrayList<ExerciseItem>) {
        this.weightLossList = weightLossList
    }

    private var muscleGainList : ArrayList<ExerciseItem> = ArrayList()
    fun getMuscleGainList() : ArrayList<ExerciseItem> {
        return this.muscleGainList
    }

    fun setMuscleGainList(muscleGainList : ArrayList<ExerciseItem>) {
        this.muscleGainList = muscleGainList
    }

    private var nutritionsItem = ArrayList<NutritionsItem>()


    fun setNutritionItems(nutritionsItem : ArrayList<NutritionsItem>) {
        this.nutritionsItem = nutritionsItem
    }

    fun getNutritionItems() : ArrayList<NutritionsItem> {
        return this.nutritionsItem
    }

    private var productList = ArrayList<Product>()

    fun getProductList(): ArrayList<Product> {
        return this.productList
    }

    fun setProductList(productList : ArrayList<Product>) {
        this.productList = productList
    }
}