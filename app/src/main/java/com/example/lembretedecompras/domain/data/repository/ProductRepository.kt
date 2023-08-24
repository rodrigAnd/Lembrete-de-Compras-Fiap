package com.example.lembretedecompras.domain.data.repository

import androidx.lifecycle.LiveData
import com.example.lembretedecompras.domain.data.dao.ProductDAO
import com.example.lembretedecompras.domain.models.Product

class ProductRepository(private val productDAO: ProductDAO) {
    val products: LiveData<List<Product>> = productDAO.getProducts()
    suspend fun insert(product: Product) {
        productDAO.insert(product)
    }

    suspend fun delete(product: Product) {
        productDAO.delete(product)
    }
    suspend fun deleteByProductName(productName: String) {
        productDAO.deleteByProductName(productName)
    }

    suspend fun deleteAll() {
        productDAO.deleteAll()
    }
}