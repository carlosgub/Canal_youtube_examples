package com.carlosgub.pizzaapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.carlosgub.pizzaapp.R
import com.carlosgub.pizzaapp.databinding.TopMenuItemBinding
import com.carlosgub.pizzaapp.model.Pizza

class RVTopMenuAdapter : RecyclerView.Adapter<RVTopMenuAdapter.ViewHolder>() {

    private var pizzaList: MutableList<Pizza> = mutableListOf()
    private var listener: Listener? = null

    interface Listener {
        fun onTopMenuSelected(pizza: Pizza)
    }

    fun setListener(listener:Listener){
        this.listener = listener
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

    override fun getItemCount(): Int = pizzaList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = pizzaList[position]
        holder.bind(item, listener)
    }

    fun addItems(list: List<Pizza>) {
        pizzaList.addAll(list)
        this.notifyDataSetChanged()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = TopMenuItemBinding.bind(view)

        fun bind(pizza: Pizza, listener: Listener?) {
            binding.ivTopMenuItemPizza.setImageResource(pizza.image)
            binding.tvTopMenuItemName.text = pizza.name
            binding.tvTopMenuItemCategory.text = pizza.category
            binding.tvTopMenuItemPrice.text = String.format(
                format = itemView.context.resources.getString(R.string.format_price),
                args = arrayOf(pizza.price)
            )
            itemView.setOnClickListener { listener?.onTopMenuSelected(pizza) }
        }
    }
}