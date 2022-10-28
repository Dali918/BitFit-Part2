package com.example.bitfit_part1

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch



private const val TAG = "AnalyticsFragment"
class AnalyticsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_analytics, container, false)
        /** Adds new food item to the database */
        lifecycleScope.launch(Dispatchers.IO) {
            //inflate with total number of calories
            var  totalCalories = (activity?.application as BitFitApplication).db.foodDao().totalCalories()
            view.findViewById<TextView?>(R.id.value_total_calories).text = totalCalories
            //inflate with total price
            var totalPrice = (activity?.application as BitFitApplication).db.foodDao().totalSpent()
            view.findViewById<TextView?>(R.id.value_total_price).text =  "$${totalPrice}"
        }


        return view

    }

    companion object {

        fun newInstance(): AnalyticsFragment{
            return AnalyticsFragment()
        }

    }
}