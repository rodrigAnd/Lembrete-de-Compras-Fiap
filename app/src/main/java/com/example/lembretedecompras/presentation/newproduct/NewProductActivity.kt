package com.example.lembretedecompras.presentation.newproduct

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import com.example.lembretedecompras.R
import com.example.lembretedecompras.databinding.ActivityNewProductBinding

class NewProductActivity : AppCompatActivity() {
    private lateinit var binding : ActivityNewProductBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewProductBinding .inflate(layoutInflater)
        setContentView( binding.root)
        initListeners()
    }

    private fun initListeners () {
        binding.btSave.setOnClickListener {
            val replyIntent = Intent()
            if (TextUtils.isEmpty(binding.etProduct.text)) {
                setResult( Activity.RESULT_CANCELED, replyIntent )
            } else {
                val word = binding.etProduct.text.toString()
                replyIntent .putExtra(EXTRA_REPLY , word)
                setResult( Activity.RESULT_OK, replyIntent )
            }
            finish()
        }
    }
    companion object {
        const val EXTRA_REPLY = "REPLY"
    }
}