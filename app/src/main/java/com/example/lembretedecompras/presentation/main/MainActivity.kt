package com.example.lembretedecompras.presentation.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lembretedecompras.R
import com.example.lembretedecompras.databinding.ActivityMainBinding
import com.example.lembretedecompras.domain.models.Product
import com.example.lembretedecompras.presentation.newproduct.NewProductActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mainViewModel: MainViewModel
    private lateinit var adapter: MainListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerView()
        initViewModel()
        initObserver()
        initListeners()
    }

    //Método para criar o menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    //Listener para escutar o clique no botão
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.btDelete -> {
                dialogDelete().show()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initListeners() {
        binding.fabNewProduct.setOnClickListener {
            val nextScreen = Intent(this, NewProductActivity::class.java)
            newProductRequest.launch(nextScreen)
        }
    }

    private val newProductRequest =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        {
            if (it.resultCode == RESULT_OK) {
                it.data?.getStringExtra(NewProductActivity.EXTRA_REPLY)?.let {
                    val product = Product(it)
                    mainViewModel.insert(product)
                }
            }
        }

    private fun initViewModel() {
        mainViewModel =
            ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private fun initObserver() {
        mainViewModel.products.observe(this, Observer { produtos ->
            produtos?.let { adapter.setProducts(it) }
        })
    }

    private fun setUpRecyclerView() {
        adapter = MainListAdapter {
            mainViewModel.delete(it)
        }

        binding.rvProducts.adapter = adapter
        binding.rvProducts.layoutManager = LinearLayoutManager(this)
    }

    private fun dialogDelete(): AlertDialog {
        return AlertDialog.Builder(this)
            .setTitle("Lembrete de Compras")
            .setMessage("Deseja apagar sua lista?")
            .setIcon(R.drawable.ic_delete)
            .setPositiveButton("Apagar") { dialog, _ ->
                mainViewModel.delete()
                dialog.dismiss()
            }
            .setNegativeButton("Cancelar") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
    }
}