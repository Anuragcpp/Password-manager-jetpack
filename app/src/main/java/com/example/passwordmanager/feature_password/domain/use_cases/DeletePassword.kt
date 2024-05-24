package com.example.passwordmanager.feature_password.domain.use_cases

import com.example.passwordmanager.feature_password.domain.model.Password
import com.example.passwordmanager.feature_password.domain.repository.PasswordRepository

class DeletePassword(
    private val passwordRepository: PasswordRepository
) {

    suspend operator fun invoke( password : Password){
        if(password.service.isNotBlank() && password.password.isNotBlank())
        passwordRepository.deletePassWord(password)
    }
}