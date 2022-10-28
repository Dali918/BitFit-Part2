package com.example.bitfit_part1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val food = mutableListOf<FoodEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /** Set up the Recycle View Food Adapter with food */
        val foodRecyclerView : RecyclerView = findViewById(R.id.foodRecyclerView)
        /**Create food adapter**/
        val foodAdapter = FoodAdapter(this, food)
        /**Update food adpter**/
        lifecycleScope.launch {
            /**Get food from database, map to food entity list, clear the data base, add all entries to adapter
             update recyclerView**/
            (application as BitFitApplication).db.foodDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    FoodEntity(entity.foodName, entity.foodCalories, entity.foodPrice)
                }.also { mappedList ->
                    food.clear()
                    food.addAll(mappedList)
                    foodAdapter.notifyDataSetChanged()
                }
            }
        }
        /**assign recycler view to food adapter**/
        foodRecyclerView.adapter = foodAdapter
        foodRecyclerView.layoutManager = LinearLayoutManager(this).also {
            val dividerItemDecoration = DividerItemDecoration(this, it.orientation)
            foodRecyclerView.addItemDecoration(dividerItemDecoration)
        }

        /** Set button onclick listener to navigate to AddFood screen*/
        findViewById<Button>(R.id.addFoodButton)?.setOnClickListener{
            val intent = Intent(this, AddFoodDetailActivity::class.java)
            startActivity(intent)
        }
    }
}