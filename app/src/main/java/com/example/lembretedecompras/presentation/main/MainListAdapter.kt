package com.example.lembretedecompras.presentation.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.lembretedecompras.databinding.ProductItemBinding
import com.example.lembretedecompras.domain.models.Product

class MainListAdapter(val listenerDelete: (Product) -> Unit) :
    RecyclerView.Adapter<MainListAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private var products = emptyList<Product>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            ViewHolder {
        val binding =
            ProductItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder (holder: ViewHolder, position: Int) {
        with(holder){
            with(products[position]){
                binding.tvProduct.text = this.name
                binding.ivDelete.setOnClickListener {
                    listenerDelete.invoke(products[position])
                }
            }
        }
    }
    internal fun setProducts (products: List<Product>) {
        this.products = products
        notifyDataSetChanged()
    }
    override fun getItemCount (): Int {
        return products.size
    }
}