package com.example.lembretedecompras.presentation.register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lembretedecompras.domain.data.repository.UserRepository
import com.example.lembretedecompras.domain.models.User
import com.example.lembretedecompras.domain.models.state.RequestRegisterState
import com.example.lembretedecompras.domain.models.state.RequestState

class RegisterViewModel(context: Application) : AndroidViewModel(context) {

    private val userRepository = UserRepository(context)

    val loggedUserState = MutableLiveData<RequestRegisterState<Nothing>>()
    fun verifyPassWord(passOne: String, passTwo: String, user: User){
        loggedUserState.value = userRepository.verifyPassword(passOne, passTwo, user).value
    }

    fun setRegisterUser(user: User) {
        userRepository.addUser(user)
    }
}