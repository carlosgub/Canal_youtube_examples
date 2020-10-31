package com.carlosgub.pizzaapp.view

import android.animation.ValueAnimator
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.carlosgub.pizzaapp.R
import com.carlosgub.pizzaapp.databinding.PizzaDetailActivityBinding
import com.carlosgub.pizzaapp.model.Pizza


class PizzaDetailActivity : AppCompatActivity() {

    companion object {
        private const val ARG_PIZZA = "arg_pizza"

        fun createInstance(context: Context, pizza: Pizza): Intent {
            return Intent(context, PizzaDetailActivity::class.java).apply {
                putExtras(Bundle().apply {
                    putParcelable(ARG_PIZZA, pizza)
                })
            }
        }
    }

    private var oldValue = 0
    private var newValue = 0
    private lateinit var binding: PizzaDetailActivityBinding
    private lateinit var pizza: Pizza
    private var quantity: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PizzaDetailActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        getExtras()
        fillScreen()
        buttonActions()

    }

    private fun getExtras() {
        intent.let {
            pizza = it.getParcelableExtra(ARG_PIZZA)!!
        }
    }

    private fun fillScreen() {
        binding.tvPizzaDetailName.text = pizza.name
        binding.ivPizzaDetailPizza.setImageResource(pizza.image)
        setPizzaQuantity(quantity, quantity)
    }

    private fun setPizzaQuantity(oldValue: Int, newValue: Int) {
        binding.tvPizzaDetailQuantity.text = "$newValue"
        startCountAnimation(
            oldValue = oldValue * pizza.price,
            newValue = newValue * pizza.price
        )
    }

    private fun buttonActions() {
        binding.ibPizzaDetailAdd.setOnClickListener {
            oldValue = quantity
            quantity++
            newValue = quantity
            setPizzaQuantity(oldValue, newValue)
        }

        binding.ibPizzaDetailMinus.setOnClickListener {
            if (quantity >= 2) {
                oldValue = quantity
                quantity--
                newValue = quantity
                setPizzaQuantity(oldValue, newValue)
            }
        }

        binding.ivPizzaDetailBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun startCountAnimation(oldValue: Float, newValue: Float) {
        val animator = ValueAnimator.ofFloat(oldValue, newValue)
        animator.duration = 500
        animator.addUpdateListener { animation ->
            binding.tvPizzaDetailTotalToPay.text =
                String.format(
                    format = resources.getString(R.string.format_price),
                    args = arrayOf(animation.animatedValue)
                )
        }
        animator.start()
    }
}