package com.example.lembretedecompras.presentation.register

import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.lembretedecompras.R
import com.example.lembretedecompras.databinding.ActivityRegisterBinding
import com.example.lembretedecompras.extensions.hideKeyboard

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    private lateinit var registerViewModel: RegisterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()

        initAnimation()

        hideKeyboard()

        initListener()

        initViewModel()

        //initObserver()
    }

    private fun initListener() {
        binding.btnCreateAccount.setOnClickListener {

        }
    }

    private fun initAnimation() {
        val animMascot = AnimationUtils.loadAnimation(this, R.anim.frombottom2)
        val animForm = AnimationUtils.loadAnimation(this, R.anim.frombottom)

        binding.containerLogin.clearAnimation()
        binding.ivLogin.clearAnimation()

        binding.containerLogin.startAnimation(animForm)
        binding.ivLogin.startAnimation(animMascot)
    }

    private fun initViewModel() {
        registerViewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
    }
}