package com.example.passwordmanager.feature_password.presentation.util

sealed class Screens (
    val route : String
) {
    object PasswordScreen : Screens("passwords")
    object AddEditPasswordScreen : Screens("add_edit_password")
}