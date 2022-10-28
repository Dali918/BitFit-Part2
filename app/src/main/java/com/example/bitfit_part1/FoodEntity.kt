package com.example.bitfit_part1
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "food_table")
data class FoodEntity (
    @ColumnInfo val foodName : String,
    @ColumnInfo val foodCalories : String,
    @ColumnInfo val foodPrice: String?,
    @PrimaryKey(autoGenerate = true) val id : Long =0,
)
