package com.example.lembretedecompras.domain.models.state

sealed class RequestRegisterState<out T> {
    object Success: RequestRegisterState<Nothing> ()
    object Fail: RequestRegisterState<Nothing> ()
}
