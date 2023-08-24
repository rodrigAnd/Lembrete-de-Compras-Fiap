package com.example.lembretedecompras.presentation.login

import android.app.Application

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.lembretedecompras.domain.data.repository.UserRepository
import com.example.lembretedecompras.domain.models.RequestState
import com.example.lembretedecompras.domain.models.User

class LoginViewModel(context: Application) : AndroidViewModel(context) {

    private val userRepository = UserRepository(context)

    val loginState = MutableLiveData<RequestState<String>>()
    val loggedUserState = MutableLiveData<RequestState<String>>()

    fun getLoggedUser() {
        loggedUserState.value = userRepository.getLoggedUser().value
    }

    fun login(user: User) {
        loginState.value = userRepository.login(user).value
    }
}


