package com.example.lembretedecompras.presentation.login

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.lembretedecompras.R
import com.example.lembretedecompras.databinding.ActivityLoginBinding
import com.example.lembretedecompras.domain.models.state.RequestState
import com.example.lembretedecompras.domain.models.User
import com.example.lembretedecompras.extensions.hideKeyboard
import com.example.lembretedecompras.presentation.main.MainActivity
import com.example.lembretedecompras.presentation.register.RegisterActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAnimation()

        hideKeyboard()

        initListener()

        initViewModel()

        initObserver()

        loginViewModel.getLoggedUser()

    }

    private fun initListener() {
        binding.btLogin.setOnClickListener {
            loginViewModel.login(
                User(
                    binding.etEmail.text.toString(),
                    binding.etPassword.text.toString()
                )
            )
        }

        binding.tvCreateNewAccount.setOnClickListener {
            startRegister()
        }

        binding.etPassword.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                binding.ivLogin.speed = 2f
                binding.ivLogin.setMinAndMaxProgress(0.0f, 0.65f)
            } else {
                binding.ivLogin.speed = 1f
                binding.ivLogin.setMinAndMaxProgress(0.65f, 1.0f)
            }
            binding.ivLogin.playAnimation()
        }
    }

    private fun startRegister() {
        val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
        launchSomeActivity.launch(intent)
    }

    private fun initViewModel() {
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
    }

    private fun initObserver() {

        loginViewModel.loginState.observe(this) {
            when (it) {
                is RequestState.Success -> {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }

                is RequestState.Error -> {
                    val animShake = AnimationUtils.loadAnimation(this, R.anim.shake)
                    binding.containerLogin.startAnimation(animShake)
                    binding.tvPasswordFeedback.text = it.throwable.message
                }

                is RequestState.Loading -> {}

                is RequestState.NewActivity -> {}
            }


        }

        loginViewModel.loggedUserState.observe(this, Observer {
            when (it) {
                is RequestState.Success -> {
                    binding.etEmail.setText(it.data)
                }

                is RequestState.Error -> {

                }

                is RequestState.Loading -> {
                }

                is RequestState.NewActivity -> {
                    val intent = Intent(this, RegisterActivity::class.java)
                    startActivity(intent)
                }
            }
        })
    }


    private fun initAnimation() {
        val animMascot = AnimationUtils.loadAnimation(this, R.anim.frombottom2)
        val animForm = AnimationUtils.loadAnimation(this, R.anim.frombottom)

        binding.containerLogin.clearAnimation()
        binding.ivLogin.clearAnimation()

        binding.containerLogin.startAnimation(animForm)
        binding.ivLogin.startAnimation(animMascot)
    }

    private val launchSomeActivity = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            result.data?.let {
                binding.etEmail.setText(it.getStringExtra("name"))
            }

        } else {
            Toast.makeText(this, "Cancelled...", Toast.LENGTH_SHORT).show()
        }
    }
}