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