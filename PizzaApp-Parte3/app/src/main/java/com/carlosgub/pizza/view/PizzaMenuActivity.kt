package com.carlosgub.pizza.view

import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.carlosgub.pizza.adapter.RVTopMenuAdapter
import com.carlosgub.pizza.data.DataDummy
import com.carlosgub.pizza.databinding.PizzaMenuActivityBinding

class PizzaMenuActivity : AppCompatActivity() {

    private lateinit var binding: PizzaMenuActivityBinding
    private val rvTopMenuAdapter = RVTopMenuAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PizzaMenuActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.tvPizzaMenuHotPromoDiscountPrice.paintFlags =
            binding.tvPizzaMenuHotPromoDiscountPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

        setRV()
    }

    private fun setRV(){
        rvTopMenuAdapter.addAll(DataDummy.getTopMenuPizza())

        binding.rvPizzaMenu.apply {
            layoutManager = LinearLayoutManager(
                this@PizzaMenuActivity,
                RecyclerView.HORIZONTAL,
                false)
            adapter = rvTopMenuAdapter
        }
    }
}