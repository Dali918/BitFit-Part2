package com.example.bitfit_part1

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.bitfit_part1.BitFitApplication
import com.example.bitfit_part1.FoodEntity
import com.example.bitfit_part1.R
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class AddFoodDetailActivity : AppCompatActivity() {
    private lateinit var foodNameInput: EditText
    private lateinit var foodPriceInput: EditText
    private lateinit var foodCaloriesInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_food_detail)

        // TODO: Find the views for the screen
        foodNameInput= findViewById(R.id.EnterFoodName)
        foodCaloriesInput= findViewById(R.id.EnterFoodCalories)
        foodPriceInput= findViewById(R.id.EnterFoodPrice)

        findViewById<Button>(R.id.addFoodButtonDetail).setOnClickListener{
            val newFood = FoodEntity(
                foodNameInput.text.toString(),
                foodCaloriesInput.text.toString(),
                foodPriceInput.text.toString()
            )

            lifecycleScope.launch(IO) {
                (application as BitFitApplication).db.foodDao().insert(newFood)
            }


            foodNameInput.getText().clear()
            foodCaloriesInput.getText().clear()
            foodPriceInput.getText().clear()
        }


    }
}