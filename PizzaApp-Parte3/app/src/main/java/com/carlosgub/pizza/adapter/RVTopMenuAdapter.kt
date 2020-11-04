package com.carlosgub.pizza.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.carlosgub.pizza.R
import com.carlosgub.pizza.databinding.TopMenuItemBinding
import com.carlosgub.pizza.model.Pizza

class RVTopMenuAdapter : RecyclerView.Adapter<RVTopMenuAdapter.ViewHolder>() {

    private var pizzaList: MutableList<Pizza> = mutableListOf()

    fun addAll(pizzaList:List<Pizza>){
        this.pizzaList.addAll(pizzaList)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ViewHolder(
            layoutInflater.inflate(
                R.layout.top_menu_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pizza = pizzaList[position]
        holder.bind(pizza)
    }

    override fun getItemCount(): Int = pizzaList.size


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = TopMenuItemBinding.bind(view)

        fun bind(pizza: Pizza) {
            binding.ivTopMenuItemPizza.setImageResource(pizza.image)
            binding.tvTopMenuItemName.text = pizza.name
            binding.tvTopMenuItemType.text = pizza.category
            binding.tvTopMenuItemPrice.text = String.format(
                format = itemView.context.resources.getString(R.string.format_price),
                args = arrayOf(pizza.price)
            )


        }
    }


}