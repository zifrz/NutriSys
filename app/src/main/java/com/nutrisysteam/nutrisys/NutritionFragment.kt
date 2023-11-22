package com.nutrisysteam.nutrisys

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.nutrisysteam.nutrisys.api.RetrofitInstance
import com.nutrisysteam.nutrisys.databinding.FragmentNutrtionBinding
import com.nutrisysteam.nutrisys.models.Nutritions
import com.nutrisysteam.nutrisys.models.NutritionsItem
import com.nutrisysteam.nutrisys.models.Product
import com.nutrisysteam.nutrisys.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.reflect.full.memberProperties


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class NutritionFragment : Fragment() {

    private var _binding: FragmentNutrtionBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var arrayList: ArrayList<String> = ArrayList()

    val properties : ArrayList<String> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentNutrtionBinding.inflate(inflater, container, false)

        addData()

        binding.tvSelect.setOnClickListener {

            val dialog = Dialog(requireContext())
            dialog.setContentView(R.layout.dialog_searchable_spinner)

            dialog.window?.setLayout(1250, 1800)

            dialog.window?.setBackgroundDrawable(object : ColorDrawable(Color.TRANSPARENT) {})

            dialog.show()

            addDataToSpinner()

            val editText : EditText = dialog.findViewById(R.id.edit_text)
            val listView : ListView = dialog.findViewById(R.id.listView)

//            val adapter : ArrayAdapter<String> = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arrayList)

            val adapter : ArrayAdapter<String> = ArrayAdapter<String>(context!!.applicationContext,android.R.layout.simple_list_item_1,arrayList)

            listView.adapter = adapter

            editText.addTextChangedListener(object: TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    adapter.filter.filter(p0)
                }

                override fun afterTextChanged(p0: Editable?) {

                }

            })

            listView.setOnItemClickListener { parent, view, position, id ->
                binding.tvSelect.text = adapter.getItem(position)
                val nutritionPath : String = adapter.getItem(position).toString()
                getDataFromAPI(nutritionPath)
                dialog.dismiss()
            }

        }

        return binding.root

    }

    private fun getDataFromAPI(path: String) {

        val progressBar = Dialog(requireContext())
        progressBar.setContentView(R.layout.progress_bar)
        progressBar.setCancelable(false)
        progressBar.create()
        progressBar.show()

        RetrofitInstance.api.getData(path).enqueue(object : Callback<Nutritions> {
            override fun onResponse(call: Call<Nutritions>, response: Response<Nutritions>) {
                if(response.isSuccessful) {
//                    Log.e("MY_TAG","Body ${response.body()}")
//                    Log.e("MY_TAG","Body ${response.body()?.size}")
                    val a = response.body()
//                    val x = response.body()!![1]
                    var count = 0

                    Constants.setNutritionItems(response.body()!!)

                    val productList = ArrayList<Product>()

                    for( x in a!!) {
                        for (prop in NutritionsItem::class.memberProperties) {
                            if(prop.get(x) is String) continue
                            if(prop.get(x) == 0 || prop.get(x) == 0.0) continue
                            Log.e("MY_TAG","${prop.name} = ${prop.get(x)}")
                            properties.add("${prop.name} = ${prop.get(x)}")
                            count += 1
                        }
                        Log.e("MY_TAG","$count")

                        var finalString = ""

                        for( i in properties) {
                            finalString += "$i\n"
                        }
                        Log.e("NEW","Final String: $finalString ")

                        val product = Product(productName = x.product_name , pURL = x.image_url, nutrition = finalString)
                        productList.add(product)

                    }

                    Constants.setProductList(productList)
                    progressBar.dismiss()
                    startActivity(Intent(context!!.applicationContext,NutritionalInformationActivity::class.java))

                }
                else {
                    Log.e("MY_TAG","error: some thing went wrong :-( ")
                    progressBar.dismiss()

                }
            }

            override fun onFailure(call: Call<Nutritions>, t: Throwable) {
                Log.e("MY_TAG","error: ${t.message}")
                progressBar.dismiss()
            }

        })
    }

    private fun addData() {
        binding.tvItemOneNamePopular.text = "Oats"
        binding.tvItemTwoNamePopular.text = "Energy Bar"
        binding.tvItemThreeNamePopular.text = "Peanut Butter"

        binding.tvOneCD.text = "Almonds"
        binding.tvTwoCD.text = "Honey"
        binding.tvThreeCD.text = "Dates"


        Glide.with(this@NutritionFragment)
            .load("https://media.post.rvohealth.io/wp-content/uploads/2020/03/oats-oatmeal-732x549-thumbnail.jpg")
            .into(binding.ivItemOnePopular)

        Glide.with(this@NutritionFragment)
            .load("https://gratefulgrazer.com/wp-content/uploads/2023/02/easy-homemade-energy-bars-with-peanut-butter-and-oats.jpg")
            .into(binding.ivItemTwoPopular)

        Glide.with(this@NutritionFragment)
            .load("https://www.simplyquinoa.com/wp-content/uploads/2022/10/honey-roasted-peanut-butter-6.jpg")
            .into(binding.ivItemThreePopular)

        Glide.with(this@NutritionFragment)
            .load("https://gonuts.in/cdn/shop/products/Salted-Almonds-Pack-of-7-Pouches-Go-Nuts-Munch-Right-1464_650x.jpg?v=1691467085%201x,//gonuts.in/cdn/shop/products/Salted-Almonds-Pack-of-7-Pouches-Go-Nuts-Munch-Right-1464_650x@2x.jpg?v=1691467085%202x")
            .into(binding.ivOneCD)

        Glide.with(this@NutritionFragment)
            .load("data:image/jpeg;base64,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")
            .into(binding.ivTwoCD)
//
        Glide.with(this@NutritionFragment)
            .load("https://rasayanam.in/wp-content/uploads/2021/09/ajwa-dates-1-1024x1024.png")
            .into(binding.ivThreeCD)
    }

    private fun addDataToSpinner() {
        arrayList.add("energy_100g");
        arrayList.add("energy_from_fat_100g");
        arrayList.add("fat_100g");
        arrayList.add("saturated_fat_100g");
        arrayList.add("_butyric_acid_100g");
        arrayList.add("_caproic_acid_100g");
        arrayList.add("_caprylic_acid_100g");
        arrayList.add("_capric_acid_100g");
        arrayList.add("_lauric_acid_100g");
        arrayList.add("_myristic_acid_100g");
        arrayList.add("_palmitic_acid_100g");
        arrayList.add("_stearic_acid_100g");
        arrayList.add("_arachidic_acid_100g");
        arrayList.add("_behenic_acid_100g");
        arrayList.add("_lignoceric_acid_100g");
        arrayList.add("_cerotic_acid_100g");
        arrayList.add("_montanic_acid_100g");
        arrayList.add("_melissic_acid_100g");
        arrayList.add("monounsaturated_fat_100g");
        arrayList.add("polyunsaturated_fat_100g");
        arrayList.add("omega_3_fat_100g");
        arrayList.add("_alpha_linolenic_acid_100g");
        arrayList.add("_eicosapentaenoic_acid_100g");
        arrayList.add("_docosahexaenoic_acid_100g");
        arrayList.add("omega_6_fat_100g");
        arrayList.add("_linoleic_acid_100g");
        arrayList.add("_arachidonic_acid_100g");
        arrayList.add("_gamma_linolenic_acid_100g");
        arrayList.add("_dihomo_gamma_linolenic_acid_100g");
        arrayList.add("omega_9_fat_100g");
        arrayList.add("_oleic_acid_100g");
        arrayList.add("_elaidic_acid_100g");
        arrayList.add("_gondoic_acid_100g");
        arrayList.add("_mead_acid_100g");
        arrayList.add("_erucic_acid_100g");
        arrayList.add("_nervonic_acid_100g");
        arrayList.add("trans_fat_100g");
        arrayList.add("cholesterol_100g");
        arrayList.add("carbohydrates_100g");
        arrayList.add("sugars_100g");
        arrayList.add("_sucrose_100g");
        arrayList.add("_glucose_100g");
        arrayList.add("_fructose_100g");
        arrayList.add("_lactose_100g");
        arrayList.add("_maltose_100g");
        arrayList.add("_maltodextrins_100g");
        arrayList.add("starch_100g");
        arrayList.add("polyols_100g");
        arrayList.add("fiber_100g");
        arrayList.add("proteins_100g");
        arrayList.add("casein_100g");
        arrayList.add("serum_proteins_100g");
        arrayList.add("nucleotides_100g");
        arrayList.add("salt_100g");
        arrayList.add("sodium_100g");
        arrayList.add("alcohol_100g");
        arrayList.add("vitamin_a_100g");
        arrayList.add("beta_carotene_100g");
        arrayList.add("vitamin_d_100g");
        arrayList.add("vitamin_e_100g");
        arrayList.add("vitamin_k_100g");
        arrayList.add("vitamin_c_100g");
        arrayList.add("vitamin_b1_100g");
        arrayList.add("vitamin_b2_100g");
        arrayList.add("vitamin_pp_100g");
        arrayList.add("vitamin_b6_100g");
        arrayList.add("vitamin_b9_100g");
        arrayList.add("folates_100g");
        arrayList.add("vitamin_b12_100g");
        arrayList.add("biotin_100g");
        arrayList.add("pantothenic_acid_100g");
        arrayList.add("silica_100g");
        arrayList.add("bicarbonate_100g");
        arrayList.add("potassium_100g");
        arrayList.add("chloride_100g");
        arrayList.add("calcium_100g");
        arrayList.add("phosphorus_100g");
        arrayList.add("iron_100g");
        arrayList.add("magnesium_100g");
        arrayList.add("zinc_100g");
        arrayList.add("copper_100g");
        arrayList.add("manganese_100g");
        arrayList.add("fluoride_100g");
        arrayList.add("selenium_100g");
        arrayList.add("chromium_100g");
        arrayList.add("molybdenum_100g");
        arrayList.add("iodine_100g");
        arrayList.add("caffeine_100g");
        arrayList.add("taurine_100g");
    }
}