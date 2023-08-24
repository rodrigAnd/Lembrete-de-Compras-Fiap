package com.example.lembretedecompras.presentation.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.lembretedecompras.domain.data.dao.LembretedeComprasRoomDatabase
import com.example.lembretedecompras.domain.data.repository.ProductRepository
import com.example.lembretedecompras.domain.models.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val productRepository: ProductRepository
    val products: LiveData<List<Product>>

    init {
        val productDAO =
            LembretedeComprasRoomDatabase.getDatabase(application).productDAO()
        productRepository = ProductRepository(productDAO)
        products = productRepository.products
    }

    fun insert(product: Product) = viewModelScope.launch(Dispatchers.IO) {
        productRepository.insert(product)
    }

    fun delete(product: Product) = viewModelScope.launch(Dispatchers.IO) {
        productRepository.delete(product)
    }

    fun delete(productName: String) = viewModelScope.launch(Dispatchers.IO) {
        productRepository.deleteByProductName(productName)
    }

    fun delete() = viewModelScope.launch(Dispatchers.IO) {
        productRepository.deleteAll()
    }
}