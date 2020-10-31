package com.carlosgub.pizzaapp.view

import android.app.Activity
import android.graphics.Paint
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView.OnEditorActionListener
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.carlosgub.pizzaapp.adapter.RVTopMenuAdapter
import com.carlosgub.pizzaapp.data.DataDummy
import com.carlosgub.pizzaapp.databinding.PizzaMenuActivityBinding
import com.carlosgub.pizzaapp.model.Pizza


class PizzaMenuActivity : AppCompatActivity(), RVTopMenuAdapter.Listener {

    private val rvTopMenuAdapter = RVTopMenuAdapter()
    private lateinit var binding: PizzaMenuActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PizzaMenuActivityBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.tvPizzaMenuHotPromoDiscountPrice.paintFlags =
            binding.tvPizzaMenuHotPromoDiscountPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

        binding.etPizzaMenuSearch.setOnEditorActionListener(OnEditorActionListener { _, actionId, _ ->
            return@OnEditorActionListener if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                hideKeyboard(this)
                true
            } else false
        })

        rvTopMenuAdapter.addItems(
            DataDummy.getTopMenuPizza()
        )

        rvTopMenuAdapter.setListener(this)

        binding.rvPizzaMenuTopMenu.apply {
            layoutManager =
                LinearLayoutManager(this@PizzaMenuActivity, RecyclerView.HORIZONTAL, false)
            adapter = rvTopMenuAdapter
        }
    }

    private fun hideKeyboard(activity: Activity) {
        val imm: InputMethodManager =
            activity.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        var view: View? = activity.currentFocus
        if (view == null) {
            view = View(activity)
        }
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    override fun onTopMenuSelected(pizza: Pizza) {
        startActivity(PizzaDetailActivity.createInstance(this@PizzaMenuActivity, pizza))
    }
}