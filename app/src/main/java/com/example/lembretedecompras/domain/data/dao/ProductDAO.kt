package com.example.lembretedecompras.domain.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lembretedecompras.domain.models.Product

@Dao
interface ProductDAO {

    @Query("SELECT * from tabela_produto ORDER BY nome_produto ASC")
    fun getProducts(): LiveData<List<Product>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(product: Product)

    @Query("DELETE FROM tabela_produto")
    suspend fun deleteAll()

    @Delete
    suspend fun delete(product: Product)

    @Query("DELETE FROM tabela_produto WHERE nome_produto = :productName")
    suspend fun deleteByProductName(productName: String)
}