package com.example.passwordmanager.feature_password.domain.use_cases

import com.example.passwordmanager.feature_password.domain.model.Password
import com.example.passwordmanager.feature_password.domain.repository.PasswordRepository
import kotlinx.coroutines.flow.Flow

class GetAllPasswords(
    private val passwordRepository: PasswordRepository
) {

    operator fun invoke() : Flow<List<Password>> {
        return passwordRepository.getAllPasswords()
    }
}