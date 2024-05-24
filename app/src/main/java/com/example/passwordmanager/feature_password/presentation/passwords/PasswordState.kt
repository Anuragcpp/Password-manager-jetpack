package com.example.passwordmanager.feature_password.presentation.passwords

import com.example.passwordmanager.feature_password.domain.model.Password

data class PasswordState(
    var passswords : List<Password> = emptyList()
)
