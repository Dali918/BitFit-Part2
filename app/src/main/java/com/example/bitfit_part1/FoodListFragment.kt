package com.example.bitfit_part1

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass.
 * Use the [FoodListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */


class FoodListFragment : Fragment() {
    private val food = mutableListOf<FoodEntity>()
    private lateinit var foodRecyclerView: RecyclerView
    private lateinit var foodAdapter: FoodAdapter



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.food_fragment_list, container, false)
        val layoutManager = LinearLayoutManager(context)
        foodRecyclerView = view.findViewById(R.id.foodRecyclerView)
        foodRecyclerView.setHasFixedSize(true)
        //set adapter with a food array
        foodAdapter = FoodAdapter(view.context, food)
        //set recycler view to adapter


        lifecycleScope.launch {
            /**Get food from database, map to food entity list, clear the data base, add all entries to adapter
            update recyclerView**/
            (activity?.application as BitFitApplication).db.foodDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    FoodEntity(entity.foodName, entity.foodCalories, entity.foodPrice)
                }.also { mappedList ->
                    food.clear()
                    food.addAll(mappedList)
                    foodAdapter.notifyDataSetChanged()

                }
            }
        }

        foodRecyclerView.adapter = foodAdapter

        layoutManager.also {
            val dividerItemDecoration = DividerItemDecoration(context, it.orientation)
            foodRecyclerView.addItemDecoration(dividerItemDecoration)
        }
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Call the new method within onViewCreated


    }

    companion object {
        fun newInstance(): FoodListFragment{
            return FoodListFragment()
        }
    }
}