package com.example.shoppingapp


import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

class MyAdapter(private val context : Activity, private val productArrayList : List<Product>) :
    RecyclerView.Adapter<MyAdapter.MyViewHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.eachitem, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return productArrayList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = productArrayList[position]
        holder.brand.text = currentItem.brand
        holder.title.text = currentItem.title
        holder.price.text = "\u20B9  ${currentItem.price}"
        holder.discount.text= "\u2193 ${currentItem.discountPercentage.toInt()}%"
         holder.ratingBar.rating = currentItem.rating.toFloat()
        // image view , how to show image in image view if the image is in form of url, 3rd Party Library
        // Picasso
        Picasso.get().load(currentItem.thumbnail).into(holder.image);



    }

    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var  brand :TextView
        var title : TextView
        var image : ShapeableImageView
        var discount : TextView
        var ratingBar : RatingBar
        var price : TextView



        init {
            title = itemView.findViewById(R.id.productTitle)
            image = itemView.findViewById(R.id.productImage)
            brand = itemView.findViewById(R.id.productbrand)
            discount = itemView.findViewById(R.id.productdiscount)
            ratingBar = itemView.findViewById(R.id.ratingBar)
            price = itemView.findViewById(R.id.productprice)
        }
    }

}