package com.example.lembretedecompras.presentation.register

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.lembretedecompras.R
import com.example.lembretedecompras.databinding.ActivityRegisterBinding
import com.example.lembretedecompras.domain.models.User
import com.example.lembretedecompras.domain.models.state.RequestRegisterState
import com.example.lembretedecompras.extensions.hideKeyboard
import com.example.lembretedecompras.presentation.login.LoginActivity

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

        initObserver()
    }

    private fun initObserver() {
        registerViewModel.loggedUserState.observe(this) { response ->
            when (response) {
                is RequestRegisterState.Success -> {
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }

                is RequestRegisterState.Fail -> {
                    val animShake = AnimationUtils.loadAnimation(this, R.anim.shake)
                    binding.containerLogin.startAnimation(animShake)
                    binding.tvPasswordFeedback.text = getString(R.string.password_invalid)
                }
            }
        }

    }

    private fun initListener() {
        binding.btnCreateAccount.setOnClickListener {
            registerViewModel.verifyPassWord(
                binding.etPassword.text.toString(),
                binding.etValidPassword.text.toString(),
                User(
                    binding.etEmail.text.toString(),
                    binding.etPassword.text.toString()
                )
            )
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