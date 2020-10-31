package com.carlosgub.pizza

import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.carlosgub.pizza.databinding.PizzaMenuActivityBinding

class PizzaMenuActivity : AppCompatActivity() {

    private lateinit var binding: PizzaMenuActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PizzaMenuActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.tvPizzaMenuHotPromoDiscountPrice.paintFlags =
            binding.tvPizzaMenuHotPromoDiscountPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG


    }
}