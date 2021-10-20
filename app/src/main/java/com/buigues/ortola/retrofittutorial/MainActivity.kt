package com.buigues.ortola.retrofittutorial

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.buigues.ortola.retrofittutorial.databinding.ActivityMainBinding
import com.buigues.ortola.retrofittutorial.service.DogsApiService
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), android.widget.SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: DogsAdapter
    private val dogImagesPaths = mutableListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.searchView.setOnQueryTextListener(this)
        initRecycler()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://dog.ceo/api/breed/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun searchByBreed(breed: String){
        CoroutineScope(Dispatchers.IO).launch {
            val request = getRetrofit().create(DogsApiService::class.java).getDogsByBreed("$breed/images")
            val response = request.body()
            runOnUiThread {
                if (request.isSuccessful) {
                    val images = response?.images ?: emptyList()
                    dogImagesPaths.clear()
                    dogImagesPaths.addAll(images)
                    adapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(applicationContext, "An error has been taken place", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private fun initRecycler() {
        val recyclerView = binding.recyclerView
        adapter = DogsAdapter(dogImagesPaths)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (!query.isNullOrEmpty()) {
            searchByBreed(query.lowercase())
        }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }
}