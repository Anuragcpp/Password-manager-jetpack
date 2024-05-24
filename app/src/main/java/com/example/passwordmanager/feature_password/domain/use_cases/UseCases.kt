package com.example.passwordmanager.feature_password.domain.use_cases

data class UseCases(
    val getAllPasswords: GetAllPasswords,
    val getPasswordById: GetPasswordById,
    val addPassword: AddPassword,
    val deletePassword: DeletePassword
)
