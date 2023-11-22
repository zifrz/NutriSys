package com.nutrisysteam.nutrisys.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nutrisysteam.nutrisys.R
import com.nutrisysteam.nutrisys.models.Product

class MyRvAdapter(private val context: Context, private val list: ArrayList<Product>) :
    RecyclerView.Adapter<MyRvAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productName: TextView = itemView.findViewById(R.id.tvProductName)
        val productImage: ImageView = itemView.findViewById(R.id.ivProductImage)
        val productDescription: TextView = itemView.findViewById(R.id.tvProductDesc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_tile, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = list[position]
        holder.productName.text = data.productName
        holder.productDescription.text = data.nutrition

        Glide.with(context)
            .load(holder.productImage)
            .error(R.drawable.nutrition_24px)
            .into(holder.productImage)
    }

}