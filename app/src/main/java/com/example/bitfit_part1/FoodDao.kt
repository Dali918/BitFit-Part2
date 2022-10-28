package com.example.bitfit_part1

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
@Dao
interface FoodDao {
    @Query("SELECT * FROM food_table")
    fun getAll(): Flow<List<FoodEntity>>

    @Insert
    fun insertAll(foods: List<FoodEntity>)

    @Insert
    fun insert(food: FoodEntity)

    @Query("SELECT sum(foodPrice) FROM food_table")
    fun totalSpent() : String

    @Query("SELECT sum(foodCalories) FROM food_table")
    fun totalCalories() : String



    @Query("DELETE FROM food_table")
    fun deleteAll()
}