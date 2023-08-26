package com.example.lembretedecompras.domain.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.lembretedecompras.domain.models.state.RequestState
import com.example.lembretedecompras.domain.models.User
import com.example.lembretedecompras.domain.models.state.RequestRegisterState

class UserRepository(val context: Context) {
    fun login(user: User): LiveData<RequestState<String>> {
        val response = MutableLiveData<RequestState<String>>()
        if (user.email == "compra@facil.com.br" &&
            user.password == "123456"
        ) {
            addUser(user)
            response.value = RequestState.Success("")
        } else {
            response.value = RequestState.Error(Exception("Usuário ou senha invalido"))
        }
        return response
    }

    fun addUser(user: User) {
        val pref = context.getSharedPreferences("lembretedecompras", 0)
        val editor = pref.edit()
        editor.putString("email", user.email)
        editor.putString("senha", user.password)
        editor.apply()
    }

    fun getLoggedUser(): LiveData<RequestState<String>> {
        val response = MutableLiveData<RequestState<String>>()
        val pref = context.getSharedPreferences("lembretedecompras", 0)
        val email = pref.getString("email", "") ?: ""
         if (!email.isNullOrBlank()) {
            response.value = RequestState.Success(email)
        } else{
             response.value =    RequestState.NewActivity(true)
        }
        return response
    }

    fun verifyPassword(passOne: String, passTwo: String, user: User): LiveData<RequestRegisterState<Nothing>> {
        val response = MutableLiveData<RequestRegisterState<Nothing>>()
        if(passOne == passTwo) {
            addUser(user)
            response.value = RequestRegisterState.Success
        } else {
            response.value = RequestRegisterState.Fail
        }
        return response
    }
}