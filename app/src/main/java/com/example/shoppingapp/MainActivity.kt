package com.example.shoppingapp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.SearchView
import android.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Locale

class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var myAdapter: MyAdapter
    lateinit var searchView: SearchView
    lateinit var temlist :Product


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        recyclerView = findViewById(R.id.recyclerView)


        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://dummyjson.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
            val retrofitData = retrofitBuilder.getProducts()

        retrofitData.enqueue(object : Callback<myDataClass?> {
            override fun onResponse(call: Call<myDataClass?>, response: Response<myDataClass?>) {
                 // if api call is success then use the data of Api and show in your app

                var responseBody = response.body()
                var productList = responseBody?.products!!






                myAdapter = MyAdapter(this@MainActivity, productList)
                recyclerView.adapter = myAdapter
                recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
            }

            override fun onFailure(p0: Call<myDataClass?>, t: Throwable) {
                // if failure

                Log.d("Main Activity ", "onFailure: " + t.message)
            }
        })
    }

//
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menuitem , menu)
//
//        var item = menu?.findItem(R.id.menuaction)
//        var searchView = item?.actionView as SearchView
//
//        searchView.setOnQueryTextListener(object : OnQueryTextListener{
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                TODO("Not yet implemented")
//
//
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                TODO("Not yet implemented")
//                var searchText = newText?.toLowerCase(Locale.getDefault())
//                if (searchText != null) {
//                    if (searchText.isNotEmpty()){
//                        temlist.title.forEach {
//                            if (searchText.let { it1 -> it.lowercase(Locale.getDefault()).contains(it1) } == true)
//
//
//                        }
//                    }
//                }
//                return false
//            }
//        })
//
//        return super.onCreateOptionsMenu(menu)
//    }

}


